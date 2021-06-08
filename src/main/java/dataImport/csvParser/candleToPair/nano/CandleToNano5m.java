package dataImport.csvParser.candleToPair.nano;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.Pair_Candle;
import hibernate.entities.nano.NANOUSDT_5minCandle;
import hibernate.entities.psgusdt.PSGUSDT_5minCandle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToNano5m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> nanousdt_5minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            NANOUSDT_5minCandle nanousdt_5minCandle = new NANOUSDT_5minCandle();
            nanousdt_5minCandle.setClose(candleData.getClose());
            nanousdt_5minCandle.setCloseTime(candleData.getCloseTime());
            nanousdt_5minCandle.setHigh(candleData.getHigh());
            nanousdt_5minCandle.setIgnore(candleData.getIgnore());
            nanousdt_5minCandle.setLow(candleData.getLow());
            nanousdt_5minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            nanousdt_5minCandle.setOpenTime(candleData.getOpenTime());
            nanousdt_5minCandle.setVolume(candleData.getVolume());
            nanousdt_5minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            nanousdt_5minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            nanousdt_5minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            nanousdt_5minCandle.setOpen(candleData.getOpen());
            nanousdt_5minCandleList.add(nanousdt_5minCandle);
        }

        return  nanousdt_5minCandleList;
    }
}
