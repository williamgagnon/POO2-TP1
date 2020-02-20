package tp1_critique.app;

import tp1_critique.actions.*;
import tp1_critique.critiquable.Review;
import tp1_critique.critiqueur.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private List<Review> reviews;
    private List<User> users;
    private User currentUser = null;
    private ActionExecutor actionExecutor;

    public final static Scanner scanner = new Scanner(System.in);

    public Application() {
        reviews = new ArrayList<>();
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
        reviews.add(new Review("bateau", "Pierre"));
        reviews.add(new Review("avion", "Theodore"));
        reviews.add(new Review("auto", "Luc"));
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

        for (Review review : reviews) {
            if (review.getTitle().equals(reponse)) {
                chooseActionOnReview(review);
                break;
            }
        }
    }

    public void printAllReviews() {
        for (Review review : reviews) {
            System.out.println(review.getTitle());
        }
    }

    private void chooseActionOnReview(Review review) {
        System.out.println();
        System.out.println("Que désirez-vous faire avec cette critique?\n");
        System.out.println("l pour lire une critique");
        System.out.println("e pour effacer la critique");

        String reponse = scanner.nextLine();

        switch (reponse) {
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
        actionExecutor.execute(RateAction.RATE, review, currentUser);
    }

    private void deleteReview(Review review) {
        String answer = actionExecutor.execute("Erase", review, currentUser);

        if (answer.equals("o")) {
            reviews.remove(review);
            System.out.println("La critique \"" + review.getTitle() + "\" a été effacée.");
        }
    }

    private void createReview() {
        Review review = new Review("", "");

        if (currentUser.getType().equals(GuestEntity.USER_TYPE)) {
            System.out.println("Désolé vous ne poouvez pas faire ça! Il faut être amateur ou profesionnel");
        } else {
            actionExecutor.execute("Create", review, currentUser);
            reviews.add(review);
            System.out.println("La critique \"" + review.getTitle() + "\" a été créée.");
        }
    }
}
