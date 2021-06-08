package dataImport.csvParser.candleToPair.btc;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.btcusdt.BTCUSDT_1minCandle;
import hibernate.entities.Pair_Candle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToBtc1m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> btcusdt_1minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            BTCUSDT_1minCandle btcusdt_1minCandle = new BTCUSDT_1minCandle();
            btcusdt_1minCandle.setClose(candleData.getClose());
            btcusdt_1minCandle.setCloseTime(candleData.getCloseTime());
            btcusdt_1minCandle.setHigh(candleData.getHigh());
            btcusdt_1minCandle.setIgnore(candleData.getIgnore());
            btcusdt_1minCandle.setLow(candleData.getLow());
            btcusdt_1minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            btcusdt_1minCandle.setOpenTime(candleData.getOpenTime());
            btcusdt_1minCandle.setVolume(candleData.getVolume());
            btcusdt_1minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            btcusdt_1minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            btcusdt_1minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            btcusdt_1minCandle.setOpen(candleData.getOpen());
            btcusdt_1minCandleList.add(btcusdt_1minCandle);
        }

        return  btcusdt_1minCandleList;
    }
}
