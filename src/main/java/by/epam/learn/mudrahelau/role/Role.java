package by.epam.learn.mudrahelau.role;

/**
 * Roles of {@link by.epam.learn.mudrahelau.model.User}
 */
public enum Role {
    /**
     * Administrator can add users, tariff plans, edit and delete them.
     */
    ADMIN("admin"),
    /**
     * Client can change private information, tariff, replenish account, request payments history.
     */
    CLIENT("client");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
