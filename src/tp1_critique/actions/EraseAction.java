package tp1_critique.actions;

import tp1_critique.app.Application;
import tp1_critique.critiquable.Review;
import tp1_critique.critiqueur.User;

public class EraseAction implements Action {
    public static final String ERASE = "Erase";

    public EraseAction() {
    }

    @Override
    public String getCode() {
        return ERASE;
    }

    @Override
    public String execute(Review review, User user) {
        String result = "";

        if (user.getType() == "Professional") {

            System.out.println("Voulez-vous vraiment effacer la critique \"" + review.getTitle() + "\" ? (o ou n)");
            String answer = Application.scanner.nextLine();

            if (answer.equalsIgnoreCase("o")) {
                result = "o";
            }
        } else if (user.getType() == "Amateur") {
            result = amateurReviewDelete(review, user);
        }

        return result;
    }

    public String amateurReviewDelete(Review review, User user) {
        String result = "";

        if (review.getAuthor().equalsIgnoreCase((user.getName()))) {
            System.out.println("Voulez-vous vraiment effacer cette critique ? (o ou n)");
            String reponse = Application.scanner.nextLine();

            if (reponse.equalsIgnoreCase("o")) {
                result = "o";
            }
        } else {
            System.out.println("Vous ne pouvez effacer que vos propres critiques.");
        }

        return result;
    }
}
