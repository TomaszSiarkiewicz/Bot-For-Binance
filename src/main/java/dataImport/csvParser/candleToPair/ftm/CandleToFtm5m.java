package dataImport.csvParser.candleToPair.ftm;

import dataImport.csvParser.CandleData;
import dataImport.csvParser.CsvParser;
import hibernate.entities.Pair_Candle;
import hibernate.entities.btcusdt.BTCUSDT_5minCandle;
import hibernate.entities.ftm.FTMUSDT_5minCandle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CandleToFtm5m {


    public static List<Pair_Candle> transfer(String filename) throws FileNotFoundException {
        CsvParser csvParser = new CsvParser();

        List<Pair_Candle> ftmusdt_5minCandleList = new ArrayList<>();

        List<CandleData>candleDataList = csvParser.parse(filename);
        for (CandleData candleData: candleDataList) {
            FTMUSDT_5minCandle ftmusdt_5minCandle = new FTMUSDT_5minCandle();
            ftmusdt_5minCandle.setClose(candleData.getClose());
            ftmusdt_5minCandle.setCloseTime(candleData.getCloseTime());
            ftmusdt_5minCandle.setHigh(candleData.getHigh());
            ftmusdt_5minCandle.setIgnore(candleData.getIgnore());
            ftmusdt_5minCandle.setLow(candleData.getLow());
            ftmusdt_5minCandle.setNumberOfTrades(candleData.getNumberOfTrades());
            ftmusdt_5minCandle.setOpenTime(candleData.getOpenTime());
            ftmusdt_5minCandle.setVolume(candleData.getVolume());
            ftmusdt_5minCandle.setQuoteAssetVolume(candleData.getQuoteAssetVolume());
            ftmusdt_5minCandle.setTakerBuyBaseAssetVolume(candleData.getTakerBuyBaseAssetVolume());
            ftmusdt_5minCandle.setTakerBuyQuoteAssetVolume(candleData.getTakerBuyQuoteAssetVolume());
            ftmusdt_5minCandle.setOpen(candleData.getOpen());
            ftmusdt_5minCandleList.add(ftmusdt_5minCandle);
        }

        return  ftmusdt_5minCandleList;
    }
}
