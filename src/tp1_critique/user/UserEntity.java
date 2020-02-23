package tp1_critique.user;

public abstract class UserEntity implements User {
    private String name;

    public UserEntity(String name) {
        assert name != null : "nom null";
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public abstract String getType();

    @Override
    public String toString() {
        return name + " {" +
                "\ntype = " + getType() + "\n}\n";
    }
}
