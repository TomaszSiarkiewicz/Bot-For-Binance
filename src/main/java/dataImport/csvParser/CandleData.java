package dataImport.csvParser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class CandleData {
    ZonedDateTime openTime;
    BigDecimal open;
    BigDecimal high;
    BigDecimal low;
    BigDecimal close;
    BigDecimal volume;
    ZonedDateTime closeTime;
    BigDecimal QuoteAssetVolume;
    int NumberOfTrades;
    BigDecimal TakerBuyBaseAssetVolume;
    BigDecimal TakerBuyQuoteAssetVolume;
    int ignore;

    protected CandleData() {
    }



    public void setOpenTime(ZonedDateTime openTime) {
        this.openTime = openTime;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public void setCloseTime(ZonedDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public void setQuoteAssetVolume(BigDecimal quoteAssetVolume) {
        QuoteAssetVolume = quoteAssetVolume;
    }

    public void setNumberOfTrades(int numberOfTrades) {
        NumberOfTrades = numberOfTrades;
    }

    public void setTakerBuyBaseAssetVolume(BigDecimal takerBuyBaseAssetVolume) {
        TakerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    }

    public void setTakerBuyQuoteAssetVolume(BigDecimal takerBuyQuoteAssetVolume) {
        TakerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
    }

    public void setIgnore(int ignore) {
        this.ignore = ignore;
    }

    public ZonedDateTime getOpenTime() {
        return openTime;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public ZonedDateTime getCloseTime() {
        return closeTime;
    }

    public BigDecimal getQuoteAssetVolume() {
        return QuoteAssetVolume;
    }

    public int getNumberOfTrades() {
        return NumberOfTrades;
    }

    public BigDecimal getTakerBuyBaseAssetVolume() {
        return TakerBuyBaseAssetVolume;
    }

    public BigDecimal getTakerBuyQuoteAssetVolume() {
        return TakerBuyQuoteAssetVolume;
    }

    public int getIgnore() {
        return ignore;
    }

    @Override
    public String toString() {
        return "CandleData{" +
                "openTime=" + openTime +
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
