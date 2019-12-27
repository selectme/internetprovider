package by.epam.learn.mudrahelau.model;

import by.epam.learn.mudrahelau.tarifftypes.TariffType;

/**
 * @author Viktar on 26.12.2019
 */
public class TariffPlan {

    private String title;
    private int speed;
    private TariffType tariffType;
    private double price;

    public TariffPlan(String title, int speed, TariffType tariffType, double price) {
        this.title = title;
        this.speed = speed;
        this.tariffType = tariffType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "TariffPlan{" +
                "title='" + title + '\'' +
                ", speed=" + speed +
                ", tariffType=" + tariffType +
                ", price=" + price +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public int getSpeed() {
        return speed;
    }

    public TariffType getTariffType() {
        return tariffType;
    }

    public double getPrice() {
        return price;
    }
}
