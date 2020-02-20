package tp1_critique.user;

import tp1_critique.app.Application;
import tp1_critique.review.SimpleReviewEntity;

public class AmateurEntity extends GuestEntity {
    public static final String USER_TYPE = "Amateur";

    public AmateurEntity(String nom) {
        super(nom);
    }

    @Override
    public String getType() {
        return USER_TYPE;
    }

    @Override
    public String toString() {
        return "Amateur{}" + super.toString();
    }
}
