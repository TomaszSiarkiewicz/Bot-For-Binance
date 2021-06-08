package dataImport.csvParser.candleToPair.btc;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.btcusdt.BTCUSDT_1minCandle;
import hibernate.entities.Pair_Candle;
import hibernate.entities.btcusdt.BTCUSDT_30minCandle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToBtc30m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> btcusdt_30minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            BTCUSDT_30minCandle btcusdt_30minCandle = new BTCUSDT_30minCandle();
            btcusdt_30minCandle.setClose(candleData.getClose());
            btcusdt_30minCandle.setCloseTime(candleData.getCloseTime());
            btcusdt_30minCandle.setHigh(candleData.getHigh());
            btcusdt_30minCandle.setIgnore(candleData.getIgnore());
            btcusdt_30minCandle.setLow(candleData.getLow());
            btcusdt_30minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            btcusdt_30minCandle.setOpenTime(candleData.getOpenTime());
            btcusdt_30minCandle.setVolume(candleData.getVolume());
            btcusdt_30minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            btcusdt_30minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            btcusdt_30minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            btcusdt_30minCandle.setOpen(candleData.getOpen());
            btcusdt_30minCandleList.add(btcusdt_30minCandle);
        }

        return  btcusdt_30minCandleList;
    }
}
