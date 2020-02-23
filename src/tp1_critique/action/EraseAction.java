package tp1_critique.action;

import tp1_critique.app.Application;
import tp1_critique.commandline.CLI;
import tp1_critique.review.Review;
import tp1_critique.review.SimpleReviewEntity;
import tp1_critique.user.User;

public class EraseAction implements Action {
    CLI cliComponent;

    public static final String ERASE = "Erase";

    public EraseAction(CLI cliComponent) {
        this.cliComponent = cliComponent;
    }

    @Override
    public String getCode() {
        return ERASE;
    }

    @Override
    public String execute(Review simpleReviewEntity, User user) {
        String result = "";

        if (user.getType() == "Professional") {
            String answer = cliComponent.printAndGetCommand("Voulez-vous vraiment effacer la critique \"" + simpleReviewEntity.getTitle() + "\" ? (o ou n)\n");

            if (answer.equalsIgnoreCase("o")) {
                result = "o";
            }
        } else if (user.getType() == "Amateur") {
            result = amateurReviewDelete(simpleReviewEntity, user);
        } else {
            System.out.println("Vous ne pouvez effacer une critique. Cette action est réservée aux amateurs et aux professionnels.");
        }

        return result;
    }

    public String amateurReviewDelete(Review simpleReviewEntity, User user) {
        String result = "";

        if (simpleReviewEntity.getAuthor().equalsIgnoreCase((user.getName()))) {
            String answer = cliComponent.printAndGetCommand("Voulez-vous vraiment effacer la critique \"" + simpleReviewEntity.getTitle() + "\" ? (o ou n)\n");

            if (answer.equalsIgnoreCase("o")) {
                result = "o";
            }
        } else {
            System.out.println("Vous ne pouvez effacer que vos propres critiques.");
        }

        return result;
    }
}
