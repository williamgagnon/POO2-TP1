package tp1_critique.critiqueur;

public abstract class UserEntity implements User {
    private String nom;

    public UserEntity(String nom) {
        assert nom != null : "nom nul";
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
