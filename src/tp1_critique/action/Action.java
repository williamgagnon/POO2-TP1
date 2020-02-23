package tp1_critique.action;

import tp1_critique.review.Review;
import tp1_critique.review.SimpleReviewEntity;
import tp1_critique.user.User;

public interface Action {
    String execute(Review review, User user);
    String getCode();
}
