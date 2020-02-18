package tp1_critique.actions;

import tp1_critique.critiquable.Review;

public interface Action {
    String execute(Review review);
    String getCode();
}
