package tp1_critique.review;

import tp1_critique.commandline.CLI;
import tp1_critique.user.User;

public class DetailedReviewEntity extends DatedReviewEntity {
    private String description;

    public DetailedReviewEntity(String title, String author, CLI cliComponent) {
        super(title, author, cliComponent);
    }

    @Override
    public void askAndFillReviewInfos(User user) {
        super.askAndFillReviewInfos(user);
        String description = getCliComponent().printAndGetCommand("Quel est la description de votre critique ?\n");

        setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + "description= " + getDescription();
    }
}
