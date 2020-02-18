package tp1_critique.app;

import tp1_critique.actions.ActionExecutor;
import tp1_critique.actions.ViewAction;
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
        actionExecutor = new ActionExecutor(new ViewAction());
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
            System.out.println(user.getNom());
        }
    }

    private String getCurrentUserName() {
        return currentUser.getNom();
    }

    /**
     * Présente le menu principal avec les actions suivantes:
     * ajouter,consulter, quitter, connecter. L'action elle même est gérée par
     * l'objet utilisateur.
     *
     * @return vrai tant que l'utilisateur n'a pas demander de quitter.
     */
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
                ajouteCritique();
                break;
            case "l":
                consulteCritique();
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

    /**
     * Permet de consulter la liste des critiques et de choisir l'action suivante:
     * effacer ou lire la critique
     */
    public void consulteCritique() {
        System.out.println("Quelle critique voulez-vous consulter ?");
        printAllReviews();

        String reponse = scanner.nextLine();

        for (int i = 0; i < reviews.size(); i++) {
            Review reviewActuelle = reviews.get(i);
            if (reviewActuelle.getTitle().equals(reponse)) {
                gereSousMenu(reviewActuelle);
            }
        }
    }

    private void ajouteCritique() {
        assert currentUser != null : "Aucun utilisateur";

        if (currentUser instanceof AmateurEntity) {
            AmateurEntity amateur = (AmateurEntity) currentUser;
            Review nouvelleReview = amateur.ajouteCritique();
            reviews.add(nouvelleReview);
        } else if (currentUser instanceof GuestEntity) {
            System.out.println("Désolé vous ne poouvez pas faire ça! Il faut être amateur ou profesionnel");
        } else {
            ProfessionalEntity professionnel = (ProfessionalEntity) currentUser;
            Review nouvelleReview = professionnel.ajouteCritique();
            reviews.add(nouvelleReview);
        }
    }

    /**
     * Demande à l'utulisateur s'il veut effacer ou consulter la critique choisie.
     *
     * @param reviewActuelle la critique à géré
     */
    private void gereSousMenu(Review reviewActuelle) {
        System.out.println();
        System.out.println("Que désirez-vous faire avec cette critique?\n");
        System.out.println("l pour lire une critique");
        System.out.println("e pour effacer la critique");

        String reponse = scanner.nextLine();

        switch (reponse) {
            case "l":
                consulteCritique(reviewActuelle);
                break;
            case "e":
                effaceCritique(reviewActuelle);
                break;
            default:
                System.out.println("Option inconnue...");
        }
    }

    /**
     * Permet de consulter une critique. Pour les invité et les amateurs, la consultation se termine par une
     * appréciation de la critique.
     *
     * @param reviewActuelle La critique à consulter et à apprécier.
     */
    private void consulteCritique(Review reviewActuelle) {
        actionExecutor.execute(ViewAction.VIEW, reviewActuelle);

        //On permet aux amateurs et aux invités d'apprécier la critique "like ot not"
        if (currentUser instanceof GuestEntity) {
            GuestEntity invite = (GuestEntity) currentUser;
            invite.apprecieCritique(reviewActuelle);
        } else if (currentUser instanceof AmateurEntity) {
            AmateurEntity amateur = (AmateurEntity) currentUser;
            amateur.apprecieCritique(reviewActuelle);
        }
        //Le professional ne peut pas faire de like.
    }

    private void effaceCritique(Review reviewAEffacer) {
        assert currentUser != null : "Aucun utilisateur";
        boolean onEfface = false;
        if (currentUser instanceof ProfessionalEntity) {
            ProfessionalEntity professionnel = (ProfessionalEntity) currentUser;
            onEfface = professionnel.effaceCritique(reviewAEffacer);
        } else if (currentUser instanceof AmateurEntity) {
            AmateurEntity professionnel = (AmateurEntity) currentUser;
            onEfface = professionnel.effaceCritique(reviewAEffacer);
        }
        if (onEfface) {
            reviews.remove(reviewAEffacer);
            System.out.println("La critique \"" + reviewAEffacer.getTitle() + "\" a été effacée.");

        }
    }

    private User findUser(String username) {
        User result = null;

        for (User user : users) {
            if (username.equals(user.getNom())) {
                result = user;
                break;
            }
        }

        return result;
    }

    public void printAllReviews() {
        for (Review review : reviews) {
            System.out.println(review.getTitle());
        }
    }
}
