package tp1_critique.user;

public class GuestEntity extends UserEntity {
    public static final String USER_TYPE = "Guest";

    public GuestEntity(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return USER_TYPE;
    }

    @Override
    public String toString() {
        return "Invite{} " + super.toString();
    }
}
