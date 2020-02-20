package tp1_critique.actions;

import tp1_critique.app.Application;
import tp1_critique.critiquable.Review;
import tp1_critique.critiqueur.ProfessionalEntity;
import tp1_critique.critiqueur.User;

import java.util.Random;

public class RateAction implements Action {
    public static final String RATE = "Rate";

    public RateAction() {
    }

    @Override
    public String getCode() {
        return RATE;
    }

    @Override
    public String execute(Review review, User user) {
        String result = "";

        System.out.println("Voulez-vous laisser une appréciation pour cette critique ? o ou n");
        String answer = Application.scanner.nextLine();

        if (user.getType().equals(ProfessionalEntity.USER_TYPE)) {
            System.out.println("Vous ne pouvez pas apprécier de critiques.");
        } else {
            if (answer.equalsIgnoreCase("o")) {
                System.out.println("Est-ce que la critique \"" + review.getTitle() + "\" vous a été utile ? o ou n");
                answer = Application.scanner.nextLine();
                if (answer.equalsIgnoreCase("o")) {
                    review.likeOrNot(user.getType(), true);
                } else {
                    review.likeOrNot(user.getType(), false);
                }
            }
        }
        
        return result;
    }
}
