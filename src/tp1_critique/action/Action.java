package tp1_critique.actions;

import tp1_critique.critiquable.Review;
import tp1_critique.users.User;

public interface Action {
    String execute(Review review, User user);
    String getCode();
}