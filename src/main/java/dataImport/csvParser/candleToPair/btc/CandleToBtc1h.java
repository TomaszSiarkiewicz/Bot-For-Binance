package dataImport.csvParser.candleToPair.btc;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.Pair_Candle;
import hibernate.entities.btcusdt.BTCUSDT_1hCandle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToBtc1h {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> btcusdt_1hCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            BTCUSDT_1hCandle btcusdt_1hCandle = new BTCUSDT_1hCandle();
            btcusdt_1hCandle.setClose(candleData.getClose());
            btcusdt_1hCandle.setCloseTime(candleData.getCloseTime());
            btcusdt_1hCandle.setHigh(candleData.getHigh());
            btcusdt_1hCandle.setIgnore(candleData.getIgnore());
            btcusdt_1hCandle.setLow(candleData.getLow());
            btcusdt_1hCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            btcusdt_1hCandle.setOpenTime(candleData.getOpenTime());
            btcusdt_1hCandle.setVolume(candleData.getVolume());
            btcusdt_1hCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            btcusdt_1hCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            btcusdt_1hCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            btcusdt_1hCandle.setOpen(candleData.getOpen());
            btcusdt_1hCandleList.add(btcusdt_1hCandle);
        }

        return  btcusdt_1hCandleList;
    }
}
