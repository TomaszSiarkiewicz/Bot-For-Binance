package hibernate.entities.tics;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


public class Tic {
    private long id;
    private ZonedDateTime closeTime;
    private float rsi;
    private float macd;
    private float emashort;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ZonedDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(ZonedDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public float getRsi() {
        return rsi;
    }

    public void setRsi(float rsi) {
        this.rsi = rsi;
    }

    public float getMacd() {
        return macd;
    }

    public void setMacd(float macd) {
        this.macd = macd;
    }

    public float getEmashort() {
        return emashort;
    }

    public void setEmashort(float emashort) {
        this.emashort = emashort;
    }


    @Override
    public String toString() {
        return "Tic{" +
                ", closeTime=" + closeTime +
                ", rsi=" + rsi +
                ", macd=" + macd +
                ", emashort=" + emashort +
                ", price=" + price +
                '}';
    }
}
