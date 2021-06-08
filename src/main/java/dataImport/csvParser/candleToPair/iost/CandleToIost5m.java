package dataImport.csvParser.candleToPair.iost;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.btcusdt.BTCUSDT_1minCandle;
import hibernate.entities.Pair_Candle;
import hibernate.entities.btcusdt.BTCUSDT_5minCandle;
import hibernate.entities.iost.IOSTUSDT_5minCandle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToIost5m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> iostusdt_5minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            IOSTUSDT_5minCandle iostusdt_5minCandle = new IOSTUSDT_5minCandle();
            iostusdt_5minCandle.setClose(candleData.getClose());
            iostusdt_5minCandle.setCloseTime(candleData.getCloseTime());
            iostusdt_5minCandle.setHigh(candleData.getHigh());
            iostusdt_5minCandle.setIgnore(candleData.getIgnore());
            iostusdt_5minCandle.setLow(candleData.getLow());
            iostusdt_5minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            iostusdt_5minCandle.setOpenTime(candleData.getOpenTime());
            iostusdt_5minCandle.setVolume(candleData.getVolume());
            iostusdt_5minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            iostusdt_5minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            iostusdt_5minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            iostusdt_5minCandle.setOpen(candleData.getOpen());
            iostusdt_5minCandleList.add(iostusdt_5minCandle);
        }

        return  iostusdt_5minCandleList;
    }
}
