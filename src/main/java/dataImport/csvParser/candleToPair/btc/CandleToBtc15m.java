package dataImport.csvParser.candleToPair.btc;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.btcusdt.BTCUSDT_15minCandle;
import hibernate.entities.btcusdt.BTCUSDT_1minCandle;
import hibernate.entities.Pair_Candle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToBtc15m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> btcusdt_15minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            BTCUSDT_15minCandle btcusdt_15minCandle = new BTCUSDT_15minCandle();
            btcusdt_15minCandle.setClose(candleData.getClose());
            btcusdt_15minCandle.setCloseTime(candleData.getCloseTime());
            btcusdt_15minCandle.setHigh(candleData.getHigh());
            btcusdt_15minCandle.setIgnore(candleData.getIgnore());
            btcusdt_15minCandle.setLow(candleData.getLow());
            btcusdt_15minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            btcusdt_15minCandle.setOpenTime(candleData.getOpenTime());
            btcusdt_15minCandle.setVolume(candleData.getVolume());
            btcusdt_15minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            btcusdt_15minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            btcusdt_15minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            btcusdt_15minCandle.setOpen(candleData.getOpen());
            btcusdt_15minCandleList.add(btcusdt_15minCandle);
        }

        return  btcusdt_15minCandleList;
    }
}
