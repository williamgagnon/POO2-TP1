package tp1_critique.app;

import tp1_critique.action.*;
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

    public final static Scanner scanner = new Scanner(System.in);

    public Application() {
        reviewEntities = new ArrayList<>();
        users = new ArrayList<>();
        initializeUsers();
        initializeReviews();
        actionExecutor = new ActionExecutor(new ViewAction(), new RateAction(), new EraseAction(), new CreateAction());
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
        reviewEntities.add(new SimpleReviewEntity("bateau", "Pierre"));
        reviewEntities.add(new SimpleReviewEntity("avion", "Theodore"));
        reviewEntities.add(new SimpleReviewEntity("auto", "Luc"));
    }

    public void run() {
        login();
        while (chooseAction()) ;
        System.out.println("Merci d'avoir utiliser ce logiciel!");
    }

    public void login() {
        System.out.println("Quel est votre nom ?\n");
        printUserNames();

        String input = scanner.nextLine();
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

        System.out.println("Que désirez-vous faire?\n");
        System.out.println("a pour ajouter une critique");
        System.out.println("l pour lire une critique");
        System.out.println("c pour changer d'utilisateur");
        System.out.println("q pour quitter");
        String answer = scanner.nextLine();

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
        System.out.println("Quelle critique voulez-vous consulter ?");
        printAllReviews();

        String reponse = scanner.nextLine();

        for (Review simpleReviewEntity : reviewEntities) {
            if (simpleReviewEntity.getTitle().equals(reponse)) {
                chooseActionOnReview(simpleReviewEntity);
                break;
            }
        }
    }

    public void printAllReviews() {
        for (Review simpleReviewEntity : reviewEntities) {
            System.out.println(simpleReviewEntity.getTitle());
        }
    }

    private void chooseActionOnReview(Review simpleReviewEntity) {
        System.out.println();
        System.out.println("Que désirez-vous faire avec cette critique?\n");
        System.out.println("l pour lire une critique");
        System.out.println("e pour effacer la critique");

        String reponse = scanner.nextLine();

        switch (reponse) {
            case "l":
                viewReviewAndPossiblyRate(simpleReviewEntity);
                break;
            case "e":
                deleteReview(simpleReviewEntity);
                break;
            default:
                System.out.println("Option inconnue...");
        }
    }

    private void viewReviewAndPossiblyRate(Review simpleReviewEntity) {
        actionExecutor.execute(ViewAction.VIEW, simpleReviewEntity, currentUser);
        actionExecutor.execute(RateAction.RATE, simpleReviewEntity, currentUser);
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
            System.out.println("Désolé vous ne poouvez pas faire ça! Il faut être amateur ou profesionnel");
        } else {
            Review review = null;

            while (review == null) {
                System.out.println("Quel type de critique souhaitez-vous créer?");
                System.out.println("S pour Simple");
                System.out.println("D pour Datée");
                System.out.println("X pour Détaillée");
                String answer = scanner.nextLine();

                switch (answer) {
                    case "S":
                        review = new SimpleReviewEntity("", "");
                        break;
                    case "D":
                        review = new DatedReviewEntity("", "");
                        break;
                    case "X":
                        review = new DetailedReviewEntity("", "");
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
