package dataImport.csvParser.candleToPair.btc;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.btcusdt.BTCUSDT_1minCandle;
import hibernate.entities.Pair_Candle;
import hibernate.entities.btcusdt.BTCUSDT_3minCandle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToBtc3m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> btcusdt_3minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            BTCUSDT_3minCandle btcusdt_3minCandle = new BTCUSDT_3minCandle();
            btcusdt_3minCandle.setClose(candleData.getClose());
            btcusdt_3minCandle.setCloseTime(candleData.getCloseTime());
            btcusdt_3minCandle.setHigh(candleData.getHigh());
            btcusdt_3minCandle.setIgnore(candleData.getIgnore());
            btcusdt_3minCandle.setLow(candleData.getLow());
            btcusdt_3minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            btcusdt_3minCandle.setOpenTime(candleData.getOpenTime());
            btcusdt_3minCandle.setVolume(candleData.getVolume());
            btcusdt_3minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            btcusdt_3minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            btcusdt_3minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            btcusdt_3minCandle.setOpen(candleData.getOpen());
            btcusdt_3minCandleList.add(btcusdt_3minCandle);
        }

        return  btcusdt_3minCandleList;
    }
}
