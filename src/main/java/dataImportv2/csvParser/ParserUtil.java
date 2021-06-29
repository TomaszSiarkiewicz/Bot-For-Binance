package dataImportv2.csvParser;

import hibernate.entities.Pair_Candle;
import hibernate.entities.aionusdt.AIONUSDT_5minCandle;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ParserUtil {

    protected static Pair_Candle migrate(RawCandleData rawCandleData) {
        Pair_Candle candleData = new Pair_Candle();
        candleData.setClose(rawCandleData.getClose());
        candleData.setCloseTime(rawCandleData.getCloseTime());
        candleData.setHigh(rawCandleData.getHigh());
        candleData.setIgnore(rawCandleData.getIgnore());
        candleData.setLow(rawCandleData.getLow());
        candleData.setNumberOfTrades(rawCandleData.getNumberOfTrades());
        candleData.setOpenTime(rawCandleData.getOpenTime());
        candleData.setOpen(rawCandleData.getOpen());
        candleData.setVolume(rawCandleData.getVolume());
        candleData.setQuoteAssetVolume(rawCandleData.getQuoteAssetVolume());
        candleData.setTakerBuyBaseAssetVolume(rawCandleData.getTakerBuyBaseAssetVolume());
        candleData.setTakerBuyQuoteAssetVolume(rawCandleData.getTakerBuyQuoteAssetVolume());

        return candleData;
    }

    private static ZonedDateTime convertTime(Long millis) {
        ZoneId zone;
        zone = ZoneId.systemDefault();
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), zone);
    }
}
