package tp1_critique.critiqueur;

import tp1_critique.app.Application;
import tp1_critique.critiquable.Review;

/**
 * Représente toute personne qui n'est pas préenregistrée dans le système.
 * Il peut:
 * - consulter les critiques
 * - apprécier une critique
 * il ne peut pas:
 * - effacer une critique
 * - ajouter une critique
 */
public class GuestEntity extends UserEntity {
    public static final String MENTION_INVITE = "Invite";

    public GuestEntity(String nom) {
        super(nom);
    }

    /**
     * Demande en console l'appréciation d'une critique à l'utilisateur. Le nom de l'invité n'Est pas retenu. Seul
     * la marque "Invite l'est.
     *
     * @param review La critique à apprécier
     */
    public void apprecieCritique(Review review) {
        System.out.println("Voulez-vous laisser une appréciation pour cette critique ? o ou n");
        String reponse = Application.scanner.nextLine();
        if (reponse.equalsIgnoreCase("o")) {
            System.out.println("Est-ce que la critique \"" + review.getTitre() + "\" vous a été utile ? o ou n");
            reponse = Application.scanner.nextLine();
            if (reponse.equalsIgnoreCase("o")) {
                review.likeOrNot(MENTION_INVITE, true);
            } else {
                review.likeOrNot(MENTION_INVITE, false);
            }
        }
    }

    @Override
    public String toString() {
        return "Invite{} " + super.toString();
    }
}
