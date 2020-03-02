package by.epam.learn.mudrahelau.constant;

/**
 * List of redirect references which used for working with servlet.
 */
public class RedirectConstants {
    public static final String MAIN_PAGE_REDIRECT = "/";
    public static final String CLIENT_ACCOUNT_REDIRECT = "do?action=show_client_account_page&user_id=";
    public static final String SHOW_TARIFFS_REDIRECT = "/do?action=show_tariffs";
    public static final String SHOW_USERS_REDIRECT = "/do?action=show_users";
    public static final String SHOW_LOGIN_PAGE_REDIRECT = "/do?action=show_login_page";
    public static final String SHOW_EDIT_USER_PAGE_BY_ADMIN_REDIRECT = "do?action=show_edit_user_page_by_admin&user_id=";
}
