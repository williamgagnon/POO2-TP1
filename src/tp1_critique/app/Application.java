package tp1_critique.app;

import tp1_critique.action.*;
import tp1_critique.commandline.CLIComponent;
import tp1_critique.review.DatedReviewEntity;
import tp1_critique.review.DetailedReviewEntity;
import tp1_critique.review.Review;
import tp1_critique.review.SimpleReviewEntity;
import tp1_critique.user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private List<Review> reviewEntities;
    private List<User> users;
    private User currentUser = null;
    private ActionExecutor actionExecutor;
    private CLIComponent cliComponent;

    public Application() {
        reviewEntities = new ArrayList<>();
        users = new ArrayList<>();
        initializeUsers();
        initializeReviews();
        cliComponent = new CLIComponent();
        actionExecutor = new ActionExecutor(new ViewAction(), new RateAction(cliComponent), new EraseAction(cliComponent), new CreateAction());
    }

    private void initializeUsers() {
        users.add(new AmateurEntity("Pierre"));
        users.add(new AmateurEntity("Luc"));
        users.add(new AmateurEntity("Charles"));

        users.add(new ProfessionalEntity("Anthem"));
        users.add(new ProfessionalEntity("Theodore"));
        users.add(new ProfessionalEntity("Matusalem"));
    }

    private void initializeReviews() {
        reviewEntities.add(new SimpleReviewEntity("Squarespace", "Pierre", cliComponent));
        reviewEntities.add(new SimpleReviewEntity("Wordpress", "Theodore", cliComponent));
        reviewEntities.add(new SimpleReviewEntity("Webflow", "Luc", cliComponent));
    }

    public void run() {
        login();
        while (chooseAction()) ;
        System.out.println("Merci d'avoir utilisé ce logiciel!");
    }

    public void login() {
        printUserNames();
        String input = cliComponent.printAndGetCommand("\nQuel est votre nom?");

        currentUser = findUser(input);

        if (currentUser == null) {
            currentUser = new GuestEntity(input);
        }
        System.out.println("Bonjour " + currentUser);
    }

    private void printUserNames() {
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

    private User findUser(String username) {
        User result = null;

        for (User user : users) {
            if (username.equals(user.getName())) {
                result = user;
                break;
            }
        }

        return result;
    }

    private String getCurrentUserName() {
        return currentUser.getName();
    }

    public boolean chooseAction() {
        boolean result = true;
        StringBuilder sb = new StringBuilder();

        sb.append("Que désirez-vous faire?\n");
        sb.append("a pour ajouter une critique\n");
        sb.append("l pour lire une critique\n");
        sb.append("c pour changer d'utilisateur\n");
        sb.append("q pour quitter\n");

        String answer = cliComponent.printAndGetCommand(sb.toString());

        switch (answer) {
            case "a":
                createReview();
                break;
            case "l":
                viewReviewAndPossiblyRate();
                break;
            case "q":
                result = false;
                break;
            case "c":
                login();
                break;
            default:
                System.out.println("Option inconnue...");
        }
        return result;
    }


    public void viewReviewAndPossiblyRate() {
        printAllReviews();
        String reponse = cliComponent.printAndGetCommand("Quelle critique voulez-vous consulter ?");

        for (Review review : reviewEntities) {
            if (review.getTitle().equals(reponse)) {
                chooseActionOnReview(review);
                break;
            }
        }
    }

    public void printAllReviews() {
        for (Review simpleReviewEntity : reviewEntities) {
            System.out.println(simpleReviewEntity.getTitle());
        }
    }

    private void chooseActionOnReview(Review review) {
        StringBuilder sb = new StringBuilder();

        sb.append(("Que désirez-vous faire avec cette critique?\n"));
        sb.append(("l pour lire une critique\n"));
        sb.append(("e pour effacer la critique\n"));

        String answer = cliComponent.printAndGetCommand(sb.toString());

        switch (answer) {
            case "l":
                viewReviewAndPossiblyRate(review);
                break;
            case "e":
                deleteReview(review);
                break;
            default:
                System.out.println("Option inconnue...");
        }
    }

    private void viewReviewAndPossiblyRate(Review review) {
        actionExecutor.execute(ViewAction.VIEW, review, currentUser);
        String answer = actionExecutor.execute(RateAction.RATE, review, currentUser);

        if (answer.equals("o")) {
            System.out.println("Vous avez apprécié la critique.");
        }
    }

    private void deleteReview(Review simpleReviewEntity) {
        String answer = actionExecutor.execute(EraseAction.ERASE, simpleReviewEntity, currentUser);

        if (answer.equals("o")) {
            reviewEntities.remove(simpleReviewEntity);
            System.out.println("La critique \"" + simpleReviewEntity.getTitle() + "\" a été effacée.");
        }
    }

    private void createReview() {
        if (currentUser.getType().equals(GuestEntity.USER_TYPE)) {
            System.out.println("Désolé, vous ne pouvez pas faire cette action. Il faut être amateur ou professionnel.");
        } else {
            Review review = null;

            while (review == null) {
                StringBuilder sb = new StringBuilder();

                sb.append("Quel type de critique souhaitez-vous créer?\n");
                sb.append("S pour Simple\n");
                sb.append("D pour Datée\n");
                sb.append("X pour Détaillée\n");

                String answer = cliComponent.printAndGetCommand(sb.toString());

                switch (answer.toLowerCase()) {
                    case "s":
                        review = new SimpleReviewEntity("", "", cliComponent);
                        break;
                    case "d":
                        review = new DatedReviewEntity("", "", cliComponent);
                        break;
                    case "x":
                        review = new DetailedReviewEntity("", "", cliComponent);
                        break;
                    default:
                        System.out.println("Option inconnue...choisis-en une bonne au moins.");
                }
            }
            actionExecutor.execute(CreateAction.CREATE, review, currentUser);
            reviewEntities.add(review);

            System.out.println("La critique \"" + review.getTitle() + "\" a été créée.");
        }
    }
}
