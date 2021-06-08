package dataImport.csvParser.candleToPair.aion;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.aionusdt.AIONUSDT_1minCandle;
import hibernate.entities.Pair_Candle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToAion1m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> aionusdt_1minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            AIONUSDT_1minCandle aionusdt_1minCandle = new AIONUSDT_1minCandle();
            aionusdt_1minCandle.setClose(candleData.getClose());
            aionusdt_1minCandle.setCloseTime(candleData.getCloseTime());
            aionusdt_1minCandle.setHigh(candleData.getHigh());
            aionusdt_1minCandle.setIgnore(candleData.getIgnore());
            aionusdt_1minCandle.setLow(candleData.getLow());
            aionusdt_1minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            aionusdt_1minCandle.setOpenTime(candleData.getOpenTime());
            aionusdt_1minCandle.setVolume(candleData.getVolume());
            aionusdt_1minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            aionusdt_1minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            aionusdt_1minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            aionusdt_1minCandle.setOpen(candleData.getOpen());
            aionusdt_1minCandleList.add(aionusdt_1minCandle);
        }

        return  aionusdt_1minCandleList;
    }
}
