package tp1_critique.review;

import tp1_critique.commandline.CLI;
import tp1_critique.user.User;

public class DatedReviewEntity extends SimpleReviewEntity {
    private String date;

    public DatedReviewEntity(String title, String author, CLI cliComponent) {
        super(title, author, cliComponent);
    }

    @Override
    public void askAndFillReviewInfos(User user) {
        super.askAndFillReviewInfos(user);
        String date = getCliComponent().printAndGetCommand("Quel est la date de votre critique ?\n");

        setDate(date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + "\ndate= " + getDate();
    }
}
