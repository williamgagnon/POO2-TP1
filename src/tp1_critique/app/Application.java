package tp1_critique.app;

import tp1_critique.actions.ActionExecutor;
import tp1_critique.actions.ViewAction;
import tp1_critique.critiquable.Review;
import tp1_critique.critiqueur.AmateurEntity;
import tp1_critique.critiqueur.GuestEntity;
import tp1_critique.critiqueur.ProfessionalEntity;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cette classe gère le scanner, la liste de critiques et la liste de personne.
 * Elle gère la boucle principale et délègue le travail aux méthodes appropriées
 * sur les classes impliquées.
 * La classe gère également l'utisateur connnecté (sans mot de passes).
 * Les utilisateurs sont prédéterminés et ils ne sont pas changeables.
 */
public class Application {
    private ArrayList<Review> reviews;
    private ArrayList<Object> utilisateurs;
    private Object utilisateurActuel = null;
    private ActionExecutor actionExecutor;

    //Le même scanner est utilisé par  toutes les classes.
    public final static Scanner scanner = new Scanner(System.in);

    public Application() {
        reviews = new ArrayList<>();
        utilisateurs = new ArrayList<>();
        initialiseUtilisateurs();
        initialiseCritique();
        actionExecutor = new ActionExecutor(new ViewAction());
    }

    /**
     * Crée tous les utilisateurs de l'application
     */
    private void initialiseUtilisateurs() {
        //Amateurs
        utilisateurs.add(new AmateurEntity("Pierre"));
        utilisateurs.add(new AmateurEntity("Luc"));
        utilisateurs.add(new AmateurEntity("Charles"));
        //Professional
        utilisateurs.add(new ProfessionalEntity("Anthem"));
        utilisateurs.add(new ProfessionalEntity("Theodore"));
        utilisateurs.add(new ProfessionalEntity("Matusalem"));
    }

    /**
     * Crée quelques critiques de base.
     */
    private void initialiseCritique() {

        reviews.add(new Review("bateau", "Pierre"));
        reviews.add(new Review("avion", "Theodore"));
        reviews.add(new Review("auto", "Luc"));
    }

    /**
     * Retourne le nom de l'utilisateur connecté.
     *
     * @return
     */
    private String getNomUtilisateurActuel() {
        String retVal = null;
        if (utilisateurActuel instanceof AmateurEntity) {
            AmateurEntity amateur = (AmateurEntity) utilisateurActuel;
            retVal = amateur.getNom();

        } else if (utilisateurActuel instanceof GuestEntity) {
            GuestEntity invite = (GuestEntity) utilisateurActuel;
            retVal = invite.getNom();
        } else {
            ProfessionalEntity professionnel = (ProfessionalEntity) utilisateurActuel;
            retVal = professionnel.getNom();
        }
        return retVal;
    }

    /**
     * Présente le menu principal avec les actions suivantes:
     * ajouter,consulter, quitter, connecter. L'action elle même est gérée par
     * l'objet utilisateur.
     *
     * @return vrai tant que l'utilisateur n'a pas demander de quitter.
     */
    public boolean choisirAction() {
        boolean retVal = true;
        System.out.println("Que désirez-vous faire?\n");
        System.out.println("a pour ajouter une critique");
        System.out.println("l pour lire une critique");
        System.out.println("c pour changer d'utilisateur");
        System.out.println("q pour quitter");
        String reponse = scanner.nextLine();

        switch (reponse) {
            case "a":
                ajouteCritique();
                break;
            case "l":
                consulteCritique();
                break;
            case "q":
                retVal = false;
                break;
            case "c":
                connecte();
                break;
            default:
                System.out.println("Option inconnue...");
        }
        return retVal;
    }

    /**
     * Permet de consulter la liste des critiques et de choisir l'action suivante:
     * effacer ou lire la critique
     */
    public void consulteCritique() {
        System.out.println("Quelle critique voulez-vous consulter ?");
        afficheToutesCritiques();

        String reponse = scanner.nextLine();

        //On retrouve la critique voulue si elle existe
        for (int i = 0; i < reviews.size(); i++) {
            Review reviewActuelle = reviews.get(i);
            if (reviewActuelle.getTitre().equals(reponse)) {
                gereSousMenu(reviewActuelle);
            }
        }
    }


    /**
     * Ajoute une critique. Seul les amateurs et les professionnels sont authorisés
     * à saisir une critique.
     */
    private void ajouteCritique() {
        assert utilisateurActuel != null : "Aucun utilisateur";

        if (utilisateurActuel instanceof AmateurEntity) {
            AmateurEntity amateur = (AmateurEntity) utilisateurActuel;
            Review nouvelleReview = amateur.ajouteCritique();
            reviews.add(nouvelleReview);
        } else if (utilisateurActuel instanceof GuestEntity) {
            System.out.println("Désolé vous ne poouvez pas faire ça! Il faut être amateur ou profesionnel");
        } else {
            ProfessionalEntity professionnel = (ProfessionalEntity) utilisateurActuel;
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
        if (utilisateurActuel instanceof GuestEntity) {
            GuestEntity invite = (GuestEntity) utilisateurActuel;
            invite.apprecieCritique(reviewActuelle);
        } else if (utilisateurActuel instanceof AmateurEntity) {
            AmateurEntity amateur = (AmateurEntity) utilisateurActuel;
            amateur.apprecieCritique(reviewActuelle);
        }
        //Le professional ne peut pas faire de like.
    }

    /**
     * Efface la critique reçu en paramètre si elle existe.
     *
     * @param reviewAEffacer La critique qui doit être effacée.
     */
    private void effaceCritique(Review reviewAEffacer) {
        assert utilisateurActuel != null : "Aucun utilisateur";
        boolean onEfface = false;
        if (utilisateurActuel instanceof ProfessionalEntity) {
            ProfessionalEntity professionnel = (ProfessionalEntity) utilisateurActuel;
            onEfface = professionnel.effaceCritique(reviewAEffacer);
        } else if (utilisateurActuel instanceof AmateurEntity) {
            AmateurEntity professionnel = (AmateurEntity) utilisateurActuel;
            onEfface = professionnel.effaceCritique(reviewAEffacer);
        }
        if (onEfface) {
            reviews.remove(reviewAEffacer);
            System.out.println("La critique \""+ reviewAEffacer.getTitre()+"\" a été effacée.");

        }
    }

    /**
     * Demande en console le nom de l'utilisateur qui veut se connecter.
     */
    public void connecte() {
        System.out.println("Quel est votre nom ?\n");
        afficheNomsUtilisateurs();

        String reponse = scanner.nextLine();
        utilisateurActuel = trouveUtilisateur(reponse);

        //Si l'utilisateur n'est pas connu c'est automatiquement un invité. On le crée donc.
        if (utilisateurActuel == null) {
            utilisateurActuel = new GuestEntity(reponse);
        }
        System.out.println("Bonjour " + utilisateurActuel);

    }

    /**
     * Affiche tous les noms des utilisateurs connus.
     */
    private void afficheNomsUtilisateurs() {
        //On affiche les noms des utilisateurs enregistrés
        for (int i = 0; i < utilisateurs.size(); i++) {
            Object utilisateur = utilisateurs.get(i);
            if (utilisateur instanceof AmateurEntity) {
                AmateurEntity amateur = (AmateurEntity) utilisateurs.get(i);
                System.out.println(amateur.getNom());
            } else if (utilisateur instanceof GuestEntity) {
                AmateurEntity invite = (AmateurEntity) utilisateurs.get(i);
                System.out.println(invite.getNom());
            } else {
                ProfessionalEntity professionnel = (ProfessionalEntity) utilisateurs.get(i);
                System.out.println(professionnel.getNom());
            }
        }
    }

    /**
     * Trouve l'utilisateur (professionnel ou amateur) qui a le nom reçue en paramètre
     * comme étant l'utilisateur connecté. pour toutes les opérations qui suivents.
     *
     * @param nomRecherche nom de la personne à connecter.
     * @return L'objet L'amateur ou le professionnel s'il a été trouvé et null sinon.
     */
    private Object trouveUtilisateur(String nomRecherche) {
        Object retVal = null;
        for (int i = 0; i < utilisateurs.size(); i++) {
            Object utilisateur = utilisateurs.get(i);
            if (utilisateur instanceof AmateurEntity) {
                AmateurEntity amateur = (AmateurEntity) utilisateurs.get(i);
                if (nomRecherche.equals(amateur.getNom())) {
                    retVal = amateur;
                }
            } else if (utilisateur instanceof GuestEntity) {
                GuestEntity invite = (GuestEntity) utilisateurs.get(i);
                if (nomRecherche.equals(invite.getNom())) {
                    retVal = invite;
                }
            } else {
                ProfessionalEntity professionnel = (ProfessionalEntity) utilisateurs.get(i);
                if (nomRecherche.equals(professionnel.getNom())) {
                    retVal = professionnel;
                }
            }
        }
        return retVal;
    }

    /**
     * Affiche toutes les titres de critique connus par l'application.
     */
    public void afficheToutesCritiques() {
        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);
            System.out.println(review.getTitre());
        }
    }


    /**
     * Lance l'application
     */
    public void run() {
        //Connecte un Professionel ou un Amateur déjà enregistré, sinon on crée un invité par défaut
        connecte();
        while (choisirAction()) ;
        System.out.println("Merci d'avoir utiliser ce logiciel!");
    }

}
