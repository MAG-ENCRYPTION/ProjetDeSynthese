package inc.notification.secure;

public enum Role {
    ADMIN, USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}