package tp1_critique.critiqueur;

public abstract class UserEntity implements User {
    private String name;

    public UserEntity(String name) {
        assert name != null : "nom nul";
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + name + '\'' +
                '}';
    }
}
