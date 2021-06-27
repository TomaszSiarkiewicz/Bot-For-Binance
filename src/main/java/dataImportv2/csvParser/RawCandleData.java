package dataImportv2.csvParser;

import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;

public class RawCandleData {

    @CsvBindByPosition(position = 0)
    Long openTime;
    @CsvBindByPosition(position = 1)
    BigDecimal open;
    @CsvBindByPosition(position = 2)
    BigDecimal high;
    @CsvBindByPosition(position = 3)
    BigDecimal low;
    @CsvBindByPosition(position = 4)
    BigDecimal close;
    @CsvBindByPosition(position = 5)
    BigDecimal volume;
    @CsvBindByPosition(position = 6)
    Long closeTime;
    @CsvBindByPosition(position = 7)
    BigDecimal QuoteAssetVolume;
    @CsvBindByPosition(position = 8)
    int NumberOfTrades;
    @CsvBindByPosition(position = 9)
    BigDecimal TakerBuyBaseAssetVolume;
    @CsvBindByPosition(position = 10)
    BigDecimal TakerBuyQuoteAssetVolume;
    @CsvBindByPosition(position = 11)
    int ignore;



    public Long getOpenTime() {
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

    public Long getCloseTime() {
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
        return "RawCandleData{" +
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
