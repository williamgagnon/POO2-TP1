package tp1_critique.review;

import tp1_critique.user.User;

public interface Review {
    void likeOrNot(String userType, boolean isLiking);

    String getTitle();

    void setTitle(String title);

    String getAuthor();

    void setAuthor(String author);

    long getGuestLikes();

    void askAndFillReviewInfos(User user);
}
