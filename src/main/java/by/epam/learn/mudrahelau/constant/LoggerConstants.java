package by.epam.learn.mudrahelau.constant;

/**
 * List of constants that used for working with {@link org.apache.logging.log4j.Logger}
 */
public class LoggerConstants {
    public static final String USER_CREATED = "User{{} {}.{}} created";
    public static final String USER_DELETED = "User{{}} deleted";
    public static final String TARIFF_DELETED = "Tariff plan{{}} deleted";
    public static final String CLIENT_EDITED = "Client{{}} edited";
    public static final String TARIFF_CREATED = "Tariff plan {title: {} speed: {}, price: {}} created";
    public static final String TARIFF_EDITED = "Tariff plan{{}} edited";
    public static final String CLIENT_CHANGED_TARIFF = "Client{{}} changed tariff plan to {{}}";
    public static final String CLIENT_MADE_PAYMENT = "Client{{}} paid {}";
    public static final String CLIENT_REQUESTED_PAYMENTS_HISTORY = "Client{{}} requested the payments history";
    public static final String CLIENT_STATUS_CHANGED = "Client{{}} status changed to {}";
    public static final String SERVER_STARTED = "Server started";
    public static final String CLIENT_PAID = "Client {{}} paid {} for the tariff {}}";
    public static final String CLIENT_DOES_NOT_HAVE_MONEY = "Client {{}} does not have enough money on the balance.";
    public static final String CLIENT_DOES_NOT_HAVE_ARREARS = "Client {} does not have any arrears";
    public static final String SQL_EXCEPTION = "SQL exception";
    public static final String NULL_POINTER_EXCEPTION = "Null pointer exception";
    public static final String NUMBER_FORMAT_EXCEPTION = "Number format exception";
    public static final String ACTION_SERVLET_EXCEPTION = "Servlet exception";



}
