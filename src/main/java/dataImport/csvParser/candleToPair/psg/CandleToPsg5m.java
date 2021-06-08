package dataImport.csvParser.candleToPair.psg;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.Pair_Candle;
import hibernate.entities.aionusdt.AIONUSDT_5minCandle;
import hibernate.entities.psgusdt.PSGUSDT_5minCandle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToPsg5m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> psgusdt_5minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            PSGUSDT_5minCandle psgusdt_5minCandle = new PSGUSDT_5minCandle();
            psgusdt_5minCandle.setClose(candleData.getClose());
            psgusdt_5minCandle.setCloseTime(candleData.getCloseTime());
            psgusdt_5minCandle.setHigh(candleData.getHigh());
            psgusdt_5minCandle.setIgnore(candleData.getIgnore());
            psgusdt_5minCandle.setLow(candleData.getLow());
            psgusdt_5minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            psgusdt_5minCandle.setOpenTime(candleData.getOpenTime());
            psgusdt_5minCandle.setVolume(candleData.getVolume());
            psgusdt_5minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            psgusdt_5minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            psgusdt_5minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            psgusdt_5minCandle.setOpen(candleData.getOpen());
            psgusdt_5minCandleList.add(psgusdt_5minCandle);
        }

        return  psgusdt_5minCandleList;
    }
}
