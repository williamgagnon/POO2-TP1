package tp1_critique.action;

import tp1_critique.commandline.CLI;
import tp1_critique.commandline.CLIComponent;
import tp1_critique.review.Review;
import tp1_critique.review.SimpleReviewEntity;
import tp1_critique.user.User;

public class ActionExecutor {
    private Action actions[];
    private CLI cliComponent;

    public ActionExecutor(Action... actions) {
        this.actions = actions;
        cliComponent = new CLIComponent();
    }

    public String execute(String code, Review review, User user) {
        String result = null;

        for (Action action : actions) {
            if (action.getCode().equals(code)) {
                result = action.execute(review, user);
                break;
            }
        }

        return result;
    }
}
