package tp1_critique.critiqueur;

import tp1_critique.app.Application;
import tp1_critique.critiquable.Critique;

/**
 * Le professionnel est payé par la compagnie qui utilise l'application. Il peut:
 * - ajouter une nouvelle critique
 * - effacer n'importe quelle critique
 * - consulter les critiques
 * il ne peut pas:
 * - apprécier une critique
 */
public class ProfessionalEntity extends UserEntity {
    private String professionalLicense;

    public ProfessionalEntity(String nom) {
        super(nom);
    }

    /**
     * Permet de déterminer si une critique peut être effacée par la personne.
     *
     * @param critique la critique a effacer
     * @return vrai si la critique peut être effacée
     */
    public boolean effaceCritique(Critique critique) {
        boolean onEfface = false;

        System.out.println("Voulez-vous vraiment effacer la critique \"" + critique.getTitre() + "\" ? (o ou n)");
        String reponse = Application.scanner.nextLine();

        if (reponse.equalsIgnoreCase("o")) {
            onEfface = true;
        }
        return onEfface;
    }

    /**
     * Permet d'ajouter une nouvelle critique. La méthode saisie en console le nom de la nouvelle critique,
     * puis crée un object Critique qui sera responsable de demander les informations qu'il a besoin en console.
     * Le Professionnel doit ajouter son numéro de licence à son nom.
     *
     * @return La critique La critique qui vient d'être créée.
     */
    public Critique ajouteCritique() {
        System.out.println("Quel est le titre de votre critique ?");
        String reponse = Application.scanner.nextLine();
        Critique nouvelleCritique = new Critique(reponse, getNom() + professionalLicense);
        nouvelleCritique.rempli();
        return nouvelleCritique;
    }

    @Override
    public String toString() {
        return "Professionnel{" +
                "professionalLicense='" + professionalLicense + '\'' +
                "} " + super.toString();
    }
}
