package tp1_critique.actions;

import tp1_critique.critiquable.Review;

public class TestUtil {
    public TestUtil() {

    }

    public static Review randomReview() {
        Review result;

        String entropy = Long.toString(System.currentTimeMillis());
        String currentAuthor = "Will" + entropy;
        String currentTitle = "Hello World" + entropy;
        result = new Review(currentTitle, currentAuthor);

        return result;
    }
}
