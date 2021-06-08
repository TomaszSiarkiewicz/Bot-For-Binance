package heatmap;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SuccesTic {

    BigDecimal min;
    BigDecimal max;
    LocalDateTime open;
    LocalDateTime close;

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public LocalDateTime getOpen() {
        return open;
    }

    public void setOpen(LocalDateTime open) {
        this.open = open;
    }

    public LocalDateTime getClose() {
        return close;
    }

    public void setClose(LocalDateTime close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "heatmap.SuccesTic{" +
                "min=" + min +
                ", max=" + max +
                ", open=" + open +
                ", close=" + close +
                '}';
    }
}
