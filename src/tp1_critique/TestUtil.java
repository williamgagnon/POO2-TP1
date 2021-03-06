package tp1_critique;

import tp1_critique.commandline.CLI;
import tp1_critique.commandline.CLIComponent;
import tp1_critique.review.SimpleReviewEntity;
import tp1_critique.user.GuestEntity;
import tp1_critique.user.User;

public class TestUtil {

    public TestUtil() {

    }

    public static SimpleReviewEntity randomReview() {
        SimpleReviewEntity result;


        CLI cliComponent = new CLIComponent();
        String currentAuthor = randomGuest().getName();
        String entropy = Long.toString(System.currentTimeMillis());
        String currentTitle = "Hello World" + entropy;
        result = new SimpleReviewEntity(currentTitle, currentAuthor, cliComponent);

        return result;
    }

    public static User randomGuest() {
        String entropy = Long.toString(System.currentTimeMillis());
        String name = "Will" + entropy;

        User user = new GuestEntity(name);

        return user;
    }
}
