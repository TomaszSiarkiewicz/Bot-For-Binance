package hibernate.entities.aionusdt;

import hibernate.entities.Pair_Candle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "AionUsdt_5min_candle_data")
public class AIONUSDT_5minCandle extends Pair_Candle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private ZonedDateTime openTime;
    @Column( precision = 14, scale = 8)
    private BigDecimal open;
    @Column( precision = 14, scale = 8)
    private BigDecimal high;
    @Column( precision = 14, scale = 8)
    private BigDecimal low;
    @Column( precision = 14, scale = 8)
    private BigDecimal close;
    @Column( precision = 19, scale = 8)
    private BigDecimal volume;
    private ZonedDateTime closeTime;
    private BigDecimal quoteAssetVolume;
    private int numberOfTrades;
    private BigDecimal takerBuyBaseAssetVolume;
    private BigDecimal takerBuyQuoteAssetVolume;
    private int ignore;

    public AIONUSDT_5minCandle() {
    }

    public AIONUSDT_5minCandle(Pair_Candle pc) {
        openTime = pc.getOpenTime();
        open = pc.getOpen();
        high = pc.getHigh();
        low = pc.getLow();
        close = pc.getClose();
        volume = pc.getVolume();
        closeTime = pc.getCloseTime();
        quoteAssetVolume = pc.getQuoteAssetVolume();
        numberOfTrades = pc.getNumberOfTrades();
        takerBuyBaseAssetVolume = pc.getTakerBuyBaseAssetVolume();
        takerBuyQuoteAssetVolume = pc.getTakerBuyQuoteAssetVolume();
        ignore = pc.getIgnore();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(ZonedDateTime openTime) {
        this.openTime = openTime;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public ZonedDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(ZonedDateTime closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public BigDecimal getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    @Override
    public void setQuoteAssetVolume(BigDecimal quoteAssetVolume) {
        this.quoteAssetVolume = quoteAssetVolume;
    }

    @Override
    public int getNumberOfTrades() {
        return numberOfTrades;
    }

    @Override
    public void setNumberOfTrades(int numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public BigDecimal getTakerBuyBaseAssetVolume() {
        return takerBuyBaseAssetVolume;
    }

    public void setTakerBuyBaseAssetVolume(BigDecimal takerBuyBaseAssetVolume) {
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    }

    public BigDecimal getTakerBuyQuoteAssetVolume() {
        return takerBuyQuoteAssetVolume;
    }

    public void setTakerBuyQuoteAssetVolume(BigDecimal takerBuyQuoteAssetVolume) {
        this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
    }

    public int getIgnore() {
        return ignore;
    }

    public void setIgnore(int ignore) {
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return "AIONUSDT_5minCandle{" +
                "id=" + id +
                ", openTime=" + openTime +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", closeTime=" + closeTime +
                ", quoteAssetVolume=" + quoteAssetVolume +
                ", numberOfTrades=" + numberOfTrades +
                ", takerBuyBaseAssetVolume=" + takerBuyBaseAssetVolume +
                ", takerBuyQuoteAssetVolume=" + takerBuyQuoteAssetVolume +
                ", ignore=" + ignore +
                '}';
    }
}
