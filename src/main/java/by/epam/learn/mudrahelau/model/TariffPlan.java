package by.epam.learn.mudrahelau.model;

import java.math.BigDecimal;

/**
 * @author Viktar on 26.12.2019
 */
public class TariffPlan {
    private int id;
    private String title;
    private int speed;
    private BigDecimal price;

    public TariffPlan() {
    }

    public TariffPlan(int id, String title, int speed, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.speed = speed;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TariffPlan{" +
                "id: " + id + ", " +
                "title='" + title + '\'' +
                ", speed=" + speed +
                ", price=" + price +
                '}';
    }
}
