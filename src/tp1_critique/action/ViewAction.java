package tp1_critique.actions;

import tp1_critique.critiquable.Review;
import tp1_critique.users.User;

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
