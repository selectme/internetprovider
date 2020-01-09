package by.epam.learn.mudrahelau.tariffplans;

/**
 * @author Viktar on 09.01.2020
 */
public enum Tariffs {
    SUPER("super"),
    SUPERXL("superxl"),
    SUPERXXL("superxxl");

    public final String title;

    private Tariffs(String plan) {
        this.title = plan;
    }
}
