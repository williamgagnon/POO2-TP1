package tp1_critique.actions;

import tp1_critique.app.Application;
import tp1_critique.critiquable.Review;
import tp1_critique.critiqueur.User;

public class CreateAction implements Action {
    public static final String CREATE = "Create";

    @Override
    public String execute(Review review, User user) {
        String result = "";

        System.out.println("Quel est le titre de votre critique ?");
        String title = Application.scanner.nextLine();

        review.setAuthor(user.getName());
        review.setTitle(title);
        review.rempli();

        return result;
    }

    @Override
    public String getCode() {
        return CREATE;
    }
}
