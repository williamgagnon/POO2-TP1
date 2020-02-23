package tp1_critique.review;

import tp1_critique.app.Application;
import tp1_critique.commandline.CLI;
import tp1_critique.commandline.CLIComponent;
import tp1_critique.user.GuestEntity;
import tp1_critique.user.User;

import java.util.ArrayList;

public class SimpleReviewEntity implements Review {
    private String title;
    private float valeurAccordee;
    private float valeurReelle;
    private long likes = 0;
    private long guestLikes = 0;
    private long dislikes = 0;
    private long guestDislikes = 0;
    private String author;
    private ArrayList<String> likers = new ArrayList<>(20);
    private CLI cliComponent;

    public SimpleReviewEntity(String title, String author, CLI cliComponent) {
        this.title = title;
        this.author = author;
        this.cliComponent = cliComponent;
    }

    @Override
    public void likeOrNot(String userType, boolean isLiking) {
        if (userType.equalsIgnoreCase(GuestEntity.USER_TYPE)) {
            if (isLiking) {
                guestLikes++;
            } else {
                guestDislikes++;
            }
        } else {
            if (isLiking) {
                likes++;
            } else {
                dislikes++;
            }
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public long getGuestLikes() {
        return guestLikes;
    }

    @Override
    public void askAndFillReviewInfos(User user) {
        String title = cliComponent.printAndGetCommand("Quel est le titre de votre critique ?\n");

        setTitle(title);
        setAuthor(user.getName());
    }

    @Override
    public String toString() {
        return "Critique :" +
                "titre='" + title + '\'' +
                ", valeurAccordee=" + valeurAccordee +
                ", valeurReelle=" + valeurReelle +
                ", nombreDeLike=" + (likes + guestLikes) +
                ", nombreDeNotLike=" + (dislikes + guestDislikes) +
                ", auteur='" + author + '\'';
    }

    protected CLI getCliComponent() {
        return cliComponent;
    }
}
