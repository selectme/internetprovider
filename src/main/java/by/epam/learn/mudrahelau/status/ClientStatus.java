package by.epam.learn.mudrahelau.status;

/**
 * {@link by.epam.learn.mudrahelau.model.Client} can have next statuses.
 */
public enum ClientStatus {
    /**
     * Active status means that {@link by.epam.learn.mudrahelau.model.Client} has no any arrears and can use the internet.
     */
    ACTIVE,
    /**
     * Inactive means that {@link by.epam.learn.mudrahelau.model.Client} has login and password but he has not yet chosen
     * a {@link by.epam.learn.mudrahelau.model.TariffPlan}.
     */
    INACTIVE,
    /**
     * Blocked means that {@link by.epam.learn.mudrahelau.model.Client} has arrears and he can't use the internet until
     * the bill is paid.
     */
    BLOCKED
}
