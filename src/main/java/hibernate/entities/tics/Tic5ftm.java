package hibernate.entities.tics;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
public class Tic5ftm extends Tic{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    ZonedDateTime closeTime;
    float rsi;
    float macd;
    float emashort;

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
                "closeTime=" + closeTime +
                ", rsi=" + rsi +
                ", macd=" + macd +
                ", emashort=" + emashort +
                '}';
    }
}
