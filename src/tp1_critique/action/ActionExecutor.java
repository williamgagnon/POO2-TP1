package tp1_critique.actions;

import tp1_critique.critiquable.Review;
import tp1_critique.users.User;

public class ActionExecutor {
    private Action actions[];

    public ActionExecutor(Action... actions) {
        this.actions = actions;
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
