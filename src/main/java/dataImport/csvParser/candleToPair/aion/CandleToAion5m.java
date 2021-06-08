package dataImport.csvParser.candleToPair.aion;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.Pair_Candle;
import hibernate.entities.aionusdt.AIONUSDT_5minCandle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToAion5m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> aionusdt_5minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            AIONUSDT_5minCandle aionusdt_5minCandle = new AIONUSDT_5minCandle();
            aionusdt_5minCandle.setClose(candleData.getClose());
            aionusdt_5minCandle.setCloseTime(candleData.getCloseTime());
            aionusdt_5minCandle.setHigh(candleData.getHigh());
            aionusdt_5minCandle.setIgnore(candleData.getIgnore());
            aionusdt_5minCandle.setLow(candleData.getLow());
            aionusdt_5minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            aionusdt_5minCandle.setOpenTime(candleData.getOpenTime());
            aionusdt_5minCandle.setVolume(candleData.getVolume());
            aionusdt_5minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            aionusdt_5minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            aionusdt_5minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            aionusdt_5minCandle.setOpen(candleData.getOpen());
            aionusdt_5minCandleList.add(aionusdt_5minCandle);
        }

        return  aionusdt_5minCandleList;
    }
}
