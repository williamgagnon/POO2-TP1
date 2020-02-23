package tp1_critique.action;

import tp1_critique.app.Application;
import tp1_critique.commandline.CLI;
import tp1_critique.review.Review;
import tp1_critique.review.SimpleReviewEntity;
import tp1_critique.user.ProfessionalEntity;
import tp1_critique.user.User;

public class RateAction implements Action {
    public static final String RATE = "Rate";
    private CLI cliComponent;

    public RateAction(CLI cliComponent) {
        this.cliComponent = cliComponent;
    }

    @Override
    public String getCode() {
        return RATE;
    }

    @Override
    public String execute(Review review, User user) {
        String result = "";

        String answer = cliComponent.printAndGetCommand("Voulez-vous laisser une appréciation pour cette critique ? o ou n");

        if (user.getType().equals(ProfessionalEntity.USER_TYPE)) {
            System.out.println("Vous ne pouvez pas apprécier de critiques.\n");
        } else {
            if (answer.equalsIgnoreCase("o")) {
                answer = cliComponent.printAndGetCommand("Est-ce que la critique \"" + review.getTitle() + "\" vous a été utile ? o ou n\n");
                if (answer.equalsIgnoreCase("o")) {
                    review.likeOrNot(user.getType(), true);
                    result = "o";
                } else {
                    review.likeOrNot(user.getType(), false);
                }
            }
        }

        return result;
    }
}
