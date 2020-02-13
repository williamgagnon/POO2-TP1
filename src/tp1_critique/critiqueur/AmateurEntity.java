package tp1_critique.critiqueur;

import tp1_critique.app.Application;
import tp1_critique.critiquable.Critique;

public class AmateurEntity extends UserEntity {

    public AmateurEntity(String nom) {
        super(nom);
    }

    /**
     * Permet d'de déterminer si une critique peut être effacée par la personne.
     * L'amateur ne peut effacer que ses propres critiques.
     *
     * @param critique la critique a effacer
     * @return vrai si la critique peut être effacée
     */
    public boolean effaceCritique(Critique critique) {
        boolean onEfface = false;
        //L'amateur ne peut affacer que ses propres critiques.
        if (critique.getAuteur().equalsIgnoreCase(getNom())) {
            System.out.println("Voulez-vous vraiment effacer cette critique ? (o ou n)");
            String reponse = Application.scanner.nextLine();

            if (reponse.equalsIgnoreCase("o")) {
                onEfface = true;
            }
        } else {
            System.out.println("Vous ne pouvez effacer que vos propres critiques.");
        }
        return onEfface;
    }

    /**
     * Permet d'ajouter une nouvelle critique. La méthode saisie en console le nom de la nouvelle critique,
     * puis crée un object Critique qui sera responsable de demander les informations qu'il a besoin en console.
     *
     * @return La critique La critique qui vient d'être créée.
     */
    public Critique ajouteCritique() {
        System.out.println("Quel est le titre de votre critique ?");
        String reponse = Application.scanner.nextLine();
        Critique nouvelleCritique = new Critique(reponse, getNom());
        nouvelleCritique.rempli();
        return nouvelleCritique;
    }

    /**
     * Demande en console l'appréciation d'une critique à l'utilisateur.
     * Le nom de l'amateur est retenue
     *
     * @param critique
     */
    public void apprecieCritique(Critique critique) {
        System.out.println("Voulez-vous laisser une appréciation pour cette critique ? o ou n");
        String reponse = Application.scanner.nextLine();
        if (reponse.equalsIgnoreCase("o")) {
            System.out.println("Est-ce que la critique  \"" + critique.getTitre() + "\" vous a été utile ? o ou n");
            reponse = Application.scanner.nextLine();
            if (reponse.equalsIgnoreCase("o")) {
                critique.likeOrNot("Amateur_" + getNom(), true);
            } else {
                critique.likeOrNot("Amateur_" + getNom(), false);
            }
        }
    }

    @Override
    public String toString() {
        return "Amateur{} " + super.toString();
    }
}
