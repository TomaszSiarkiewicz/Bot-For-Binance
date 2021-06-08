package dataImport;

import dataImport.csvParser.candleToPair.aion.CandleToAion1m;
import dataImport.csvParser.candleToPair.aion.CandleToAion5m;
import dataImport.csvParser.candleToPair.btc.*;
import dataImport.csvParser.candleToPair.ftm.CandleToFtm5m;
import dataImport.csvParser.candleToPair.iost.CandleToIost5m;
import dataImport.csvParser.candleToPair.nano.CandleToNano5m;
import dataImport.csvParser.candleToPair.psg.CandleToPsg5m;
import enums.Pairs;
import enums.Tics;
import hibernate.DbConnector;
import hibernate.entities.Pair_Candle;

import java.io.FileNotFoundException;
import java.util.List;

public class DataSaver {
    private final static DbConnector dbConnector = new DbConnector();

    public void save(Pairs pair, Tics tic, List<String> unzipedFileList) throws FileNotFoundException {
        switch (pair) {
            case FTM_USDT:
                if (tic == Tics.MIN_5) {
                    saveFtm5min(unzipedFileList);
                    break;
                }
            case AION_USDT:
                if (tic == Tics.MIN_1) {
                    saveAion1min(unzipedFileList);
                    break;
                }
                if (tic == Tics.MIN_3) {
                    break;
                }
                if (tic == Tics.MIN_5) {
                    saveAion5min(unzipedFileList);
                    break;
                }
                if (tic == Tics.MIN_15) {
                    break;
                }
            case PSG_USDT:{
                if (tic == Tics.MIN_5) {
                    savePsg5min(unzipedFileList);
                    break;
                }
            }
            case IOST_USDT:{
                if (tic == Tics.MIN_5) {
                    saveIost5min(unzipedFileList);
                    break;
                }
            }
            case BTC_USDT:
                if (tic == Tics.MIN_1) {
                    saveBtc1min(unzipedFileList);
                    break;
                }
                if (tic == Tics.MIN_3) {
                    saveBtc3min(unzipedFileList);
                    break;
                }
                if (tic == Tics.MIN_5) {
                    saveBtc5min(unzipedFileList);
                    break;
                }
                if (tic == Tics.MIN_15) {
                    saveBtc15min(unzipedFileList);
                    break;
                }
                if (tic == Tics.H_1) {
                    saveBtc1h(unzipedFileList);
                    break;
                }
                if (tic == Tics.MIN_30) {
                    saveBtc30min(unzipedFileList);
                    break;
                }
            case NANO_USDT:
                if (tic == Tics.MIN_5){
                    saveNano5min(unzipedFileList);
                }
        }
    }

    private void saveBtc1min(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> btcusdt_1minCandles = CandleToBtc1m.transfer(fileCsv);
            dbConnector.putInDatabase(btcusdt_1minCandles);
        }
    }
    private void saveBtc1h(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> btcusdt_1hCandles = CandleToBtc1h.transfer(fileCsv);
            dbConnector.putInDatabase(btcusdt_1hCandles);
        }
    }
    private void saveBtc5min(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> btcusdt_5minCandles = CandleToBtc5m.transfer(fileCsv);
            dbConnector.putInDatabase(btcusdt_5minCandles);
        }
    }
    private void saveFtm5min(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> ftmusdt_5minCandles = CandleToFtm5m.transfer(fileCsv);
            dbConnector.putInDatabase(ftmusdt_5minCandles);
        }
    }
    private void saveBtc15min(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> btcusdt_15minCandles = CandleToBtc15m.transfer(fileCsv);
            dbConnector.putInDatabase(btcusdt_15minCandles);
        }
    }
    private void saveBtc30min(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> btcusdt_30minCandles = CandleToBtc30m.transfer(fileCsv);
            dbConnector.putInDatabase(btcusdt_30minCandles);
        }
    }
    private void saveBtc3min(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> btcusdt_3minCandles = CandleToBtc3m.transfer(fileCsv);
            dbConnector.putInDatabase(btcusdt_3minCandles);
        }
    }

    private void saveAion5min(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> aionusdt_5minCandles = CandleToAion5m.transfer(fileCsv);
            dbConnector.putInDatabase(aionusdt_5minCandles);
        }
    }
    private void savePsg5min(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> psgusdt_5minCandles = CandleToPsg5m.transfer(fileCsv);
            dbConnector.putInDatabase(psgusdt_5minCandles);
        }
    }
    private void saveIost5min(List<String> unzipedFileList) throws FileNotFoundException {
        for (String fileCsv : unzipedFileList) {
            List<Pair_Candle> iostusdt_5minCandles = CandleToIost5m.transfer(fileCsv);
            dbConnector.putInDatabase(iostusdt_5minCandles);
        }
    }

    private void saveAion1min(List<String> unzipedFilesList) throws FileNotFoundException {
        for (String fileCsv : unzipedFilesList) {
            List<Pair_Candle> aionusdt_1minCandles = CandleToAion1m.transfer(fileCsv);
            dbConnector.putInDatabase(aionusdt_1minCandles);
        }
    }
    private void saveNano5min(List<String> unzipedFilesList) throws FileNotFoundException {
        for (String fileCsv : unzipedFilesList) {
            List<Pair_Candle> nanousdt_5minCandles = CandleToNano5m.transfer(fileCsv);
            dbConnector.putInDatabase(nanousdt_5minCandles);
        }
    }

}
