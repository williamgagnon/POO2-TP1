package tp1_critique.action;

import tp1_critique.review.Review;
import tp1_critique.user.User;

public class CreateAction implements Action {
    public static final String CREATE = "Create";

    @Override
    public String execute(Review review, User user) {
        String result = "";

        review.askAndFillReviewInfos(user);

        return result;
    }

    @Override
    public String getCode() {
        return CREATE;
    }
}
