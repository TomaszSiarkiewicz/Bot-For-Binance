package dataImport.csvParser.candleToPair.btc;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.btcusdt.BTCUSDT_1minCandle;
import hibernate.entities.Pair_Candle;
import hibernate.entities.btcusdt.BTCUSDT_5minCandle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToBtc5m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> btcusdt_5minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            BTCUSDT_5minCandle btcusdt_5minCandle = new BTCUSDT_5minCandle();
            btcusdt_5minCandle.setClose(candleData.getClose());
            btcusdt_5minCandle.setCloseTime(candleData.getCloseTime());
            btcusdt_5minCandle.setHigh(candleData.getHigh());
            btcusdt_5minCandle.setIgnore(candleData.getIgnore());
            btcusdt_5minCandle.setLow(candleData.getLow());
            btcusdt_5minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            btcusdt_5minCandle.setOpenTime(candleData.getOpenTime());
            btcusdt_5minCandle.setVolume(candleData.getVolume());
            btcusdt_5minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            btcusdt_5minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            btcusdt_5minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            btcusdt_5minCandle.setOpen(candleData.getOpen());
            btcusdt_5minCandleList.add(btcusdt_5minCandle);
        }

        return  btcusdt_5minCandleList;
    }
}
