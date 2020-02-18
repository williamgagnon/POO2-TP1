package tp1_critique.actions;

import tp1_critique.critiquable.Review;

public class ViewAction implements Action {
    public static final String VIEW = "View";

    public ViewAction() {
    }

    public String execute(Review review) {
        String result = review.toString();

        System.out.println(result);

        return result;
    }

    @Override
    public String getCode() {
        return VIEW;
    }
}
