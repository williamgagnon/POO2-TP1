package tp1_critique.user;

import tp1_critique.app.Application;
import tp1_critique.review.SimpleReviewEntity;

public class ProfessionalEntity extends UserEntity {
    public static final String USER_TYPE = "Professional";

    private String professionalLicense;

    public ProfessionalEntity(String nom) {
        super(nom);
    }

    @Override
    public String getType() {
        return USER_TYPE;
    }

    @Override
    public String toString() {
        return "Professionnel{" +
                "professionalLicense='" + professionalLicense + '\'' +
                "} " + super.toString();
    }
}
