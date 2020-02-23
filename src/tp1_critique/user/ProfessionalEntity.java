package tp1_critique.user;

import tp1_critique.app.Application;
import tp1_critique.review.SimpleReviewEntity;

public class ProfessionalEntity extends UserEntity {
    private String professionalLicense = "Diplomé de l'université Gagnon.";

    public static final String USER_TYPE = "Professional";

    public ProfessionalEntity(String nom) {
        super(nom);
    }

    @Override
    public String getType() {
        return USER_TYPE;
    }

    @Override
    public String toString() {
        return super.toString() + "\nprofessionalLiscense = " + professionalLicense + "\n}" + "\n";
    }
}
