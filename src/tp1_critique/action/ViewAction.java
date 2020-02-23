package tp1_critique.action;

import tp1_critique.review.Review;
import tp1_critique.review.SimpleReviewEntity;
import tp1_critique.user.User;

public class ViewAction implements Action {
    public static final String VIEW = "View";

    public ViewAction() {
    }

    @Override
    public String getCode() {
        return VIEW;
    }

    public String execute(Review review, User user) {
        String result = review.toString();

        System.out.println(result);

        return result;
    }
}
