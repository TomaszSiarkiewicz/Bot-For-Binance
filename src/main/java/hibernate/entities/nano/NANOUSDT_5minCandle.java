package hibernate.entities.nano;

import hibernate.entities.Pair_Candle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "NanoUsdt_5min_candle_data")
public class NANOUSDT_5minCandle extends Pair_Candle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long openTime;
    @Column(precision = 14, scale = 8)
    private BigDecimal open;
    @Column(precision = 14, scale = 8)
    private BigDecimal high;
    @Column(precision = 14, scale = 8)
    private BigDecimal low;
    @Column(precision = 14, scale = 8)
    private BigDecimal close;
    @Column(precision = 19, scale = 8)
    private BigDecimal volume;
    private long closeTime;
    private BigDecimal QuoteAssetVolume;
    private int NumberOfTrades;
    private BigDecimal TakerBuyBaseAssetVolume;
    private BigDecimal TakerBuyQuoteAssetVolume;
    private int ignore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    @Override
    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    public BigDecimal getQuoteAssetVolume() {
        return QuoteAssetVolume;
    }

    public void setQuoteAssetVolume(BigDecimal quoteAssetVolume) {
        QuoteAssetVolume = quoteAssetVolume;
    }

    public int getNumberOfTrades() {
        return NumberOfTrades;
    }

    public void setNumberOfTrades(int numberOfTrades) {
        NumberOfTrades = numberOfTrades;
    }

    public BigDecimal getTakerBuyBaseAssetVolume() {
        return TakerBuyBaseAssetVolume;
    }

    public void setTakerBuyBaseAssetVolume(BigDecimal takerBuyBaseAssetVolume) {
        TakerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    }

    public BigDecimal getTakerBuyQuoteAssetVolume() {
        return TakerBuyQuoteAssetVolume;
    }

    public void setTakerBuyQuoteAssetVolume(BigDecimal takerBuyQuoteAssetVolume) {
        TakerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
    }

    public int getIgnore() {
        return ignore;
    }

    public void setIgnore(int ignore) {
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return "AIONUSDT_1minCandle{" +
                "id=" + id +
                ", openTime=" + openTime +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", closeTime=" + closeTime +
                ", QuoteAssetVolume=" + QuoteAssetVolume +
                ", NumberOfTrades=" + NumberOfTrades +
                ", TakerBuyBaseAssetVolume=" + TakerBuyBaseAssetVolume +
                ", TakerBuyQuoteAssetVolume=" + TakerBuyQuoteAssetVolume +
                ", ignore=" + ignore +
                '}';
    }
}
