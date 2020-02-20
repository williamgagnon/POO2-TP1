package tp1_critique.critiqueur;

import tp1_critique.app.Application;
import tp1_critique.critiquable.Review;

public class AmateurEntity extends GuestEntity {
    public static final String USER_TYPE = "Amateur";

    public AmateurEntity(String nom) {
        super(nom);
    }

    @Override
    public String getType() {
        return USER_TYPE;
    }

    /**
     * Permet d'ajouter une nouvelle critique. La méthode saisie en console le nom de la nouvelle critique,
     * puis crée un object Critique qui sera responsable de demander les informations qu'il a besoin en console.
     *
     * @return La critique La critique qui vient d'être créée.
     */
    public Review ajouteCritique() {
        System.out.println("Quel est le titre de votre critique ?");
        String reponse = Application.scanner.nextLine();
        Review nouvelleReview = new Review(reponse, getName());
        nouvelleReview.rempli();
        return nouvelleReview;
    }

    @Override
    public String toString() {
        return "Amateur{}" + super.toString();
    }
}
