package inc.yowyob.service.notification.secure;

public enum Role {
    ADMIN, USER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}