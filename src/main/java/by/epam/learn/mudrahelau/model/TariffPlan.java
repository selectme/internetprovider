package by.epam.learn.mudrahelau.model;

/**
 * @author Viktar on 26.12.2019
 */
public class TariffPlan {
    private int id;
    private String title;
    private int speed;
    private double price;

    public TariffPlan(int id, String title, int speed, double price) {
        this.id = id;
        this.title = title;
        this.speed = speed;
        this.price = price;
    }

    public TariffPlan(String title, int speed, double price) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TariffPlan{" +
                "title='" + title + '\'' +
                ", speed=" + speed +
                ", price=" + price +
                '}';
    }
}
