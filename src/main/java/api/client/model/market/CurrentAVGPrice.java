package api.client.model.market;

import java.math.BigDecimal;

public class CurrentAVGPrice {
    int mins;
    BigDecimal price;

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CurrentAVGPrice{" +
                "mins=" + mins +
                ", price=" + price +
                '}';
    }
}
