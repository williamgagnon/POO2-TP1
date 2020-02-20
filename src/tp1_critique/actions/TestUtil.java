package tp1_critique.actions;

import tp1_critique.critiquable.Review;
import tp1_critique.critiqueur.GuestEntity;
import tp1_critique.critiqueur.User;

public class TestUtil {

    public TestUtil() {

    }

    public static Review randomReview() {
        Review result;

        String currentAuthor = randomGuest().getName();
        String entropy = Long.toString(System.currentTimeMillis());
        String currentTitle = "Hello World" + entropy;
        result = new Review(currentTitle, currentAuthor);

        return result;
    }

    public static User randomGuest() {
        String entropy = Long.toString(System.currentTimeMillis());
        String name = "Will" + entropy;

        User user = new GuestEntity(name);

        return user;
    }
}
