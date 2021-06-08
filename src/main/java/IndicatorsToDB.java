import enums.Pairs;
import hibernate.DbConnector;
import hibernate.entities.*;
import hibernate.entities.tics.*;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import java.util.ArrayList;
import java.util.List;

public class IndicatorsToDB {

    public static void putIndicatorsInDb(List<Pair_Candle> databaseData, int tic, Pairs pair) {
        System.out.println("putIndicatorsInDb");
        int rsiBarCount = 30;

        DbConnector dbConnector = new DbConnector();
        BarSeries series = new BaseBarSeriesBuilder().withName("AXP_Stock").build();

        for (Pair_Candle pc : databaseData) {
            series.addBar(pc.getCloseTime(), pc.getOpen(), pc.getHigh(), pc.getLow(), pc.getClose(), pc.getVolume());
        }

        ClosePriceIndicator indicator = new ClosePriceIndicator(series);


        RSIIndicator rsi = new RSIIndicator(indicator, rsiBarCount);
        MACDIndicator macd = new MACDIndicator(rsi);
        EMAIndicator emaShort = new EMAIndicator(rsi, 9);
        int maxIndex = indicator.getBarSeries().getEndIndex();

        if (pair.equals(Pairs.BTC_USDT)) {
            if (tic == 5) {
                System.out.println("5min");
                List<Tic5btc> listForDB = new ArrayList<>();
                for (int i = 30; i < maxIndex; i++) {
                    Tic5btc tic5 = new Tic5btc();
                    tic5.setRsi(rsi.getValue(i).floatValue());
                    tic5.setMacd(macd.getValue(i).floatValue());
                    tic5.setEmashort(emaShort.getValue(i).floatValue());
                    tic5.setCloseTime(series.getBar(i).getEndTime());
                    listForDB.add(tic5);
                }
                dbConnector.putIndicatorsInDatabaset5(listForDB);
            }
            if (tic == 15) {
                System.out.println("15min");
                List<Tic15> listForDB = new ArrayList<>();
                for (int i = 30; i < maxIndex; i++) {
                    Tic15 tic15 = new Tic15();
                    tic15.setRsi(rsi.getValue(i).floatValue());
                    tic15.setMacd(macd.getValue(i).floatValue());
                    tic15.setEmashort(emaShort.getValue(i).floatValue());
                    tic15.setCloseTime(series.getBar(i).getEndTime());
                    listForDB.add(tic15);
                }
                dbConnector.putIndicatorsInDatabaset15(listForDB);
            }
            if (tic == 30) {
                System.out.println("30min");
                List<Tic30> listForDB = new ArrayList<>();
                for (int i = 30; i < maxIndex; i++) {
                    Tic30 tic30 = new Tic30();
                    tic30.setRsi(rsi.getValue(i).floatValue());
                    tic30.setMacd(macd.getValue(i).floatValue());
                    tic30.setEmashort(emaShort.getValue(i).floatValue());
                    tic30.setCloseTime(series.getBar(i).getEndTime());
                    listForDB.add(tic30);
                }
                dbConnector.putIndicatorsInDatabaset30(listForDB);
            }
        }
        if (pair.equals(Pairs.AION_USDT)) {
            if (tic == 5) {
                System.out.println("5min");
                List<Tic5aion> listForDB = new ArrayList<>();
                for (int i = 30; i < maxIndex; i++) {
                    Tic5aion tic5 = new Tic5aion();
                    tic5.setRsi(rsi.getValue(i).floatValue());
                    tic5.setMacd(macd.getValue(i).floatValue());
                    tic5.setEmashort(emaShort.getValue(i).floatValue());
                    tic5.setCloseTime(series.getBar(i).getEndTime());
                    listForDB.add(tic5);
                }
                dbConnector.putIndicatorsInDatabaset5Aion(listForDB);
            }
        }
        if (pair.equals(Pairs.PSG_USDT)) {
            if (tic == 5) {
                System.out.println("5min");
                List<Tic5psg> listForDB = new ArrayList<>();
                for (int i = 30; i < maxIndex; i++) {
                    Tic5psg tic5 = new Tic5psg();
                    tic5.setRsi(rsi.getValue(i).floatValue());
                    tic5.setMacd(macd.getValue(i).floatValue());
                    tic5.setEmashort(emaShort.getValue(i).floatValue());
                    tic5.setCloseTime(series.getBar(i).getEndTime());
                    listForDB.add(tic5);
                }
                dbConnector.putIndicatorsInDatabaset5Psg(listForDB);
            }
        }
        if (pair.equals(Pairs.NANO_USDT)) {
            if (tic == 5) {
                System.out.println("5min");
                List<Tic5nano> listForDB = new ArrayList<>();
                for (int i = 30; i < maxIndex; i++) {
                    Tic5nano tic5 = new Tic5nano();
                    tic5.setRsi(rsi.getValue(i).floatValue());
                    tic5.setMacd(macd.getValue(i).floatValue());
                    tic5.setEmashort(emaShort.getValue(i).floatValue());
                    tic5.setCloseTime(series.getBar(i).getEndTime());
                    listForDB.add(tic5);
                }
                dbConnector.putIndicatorsInDatabaset5Nano(listForDB);
            }
        }

        if (pair.equals(Pairs.IOST_USDT)) {
            if (tic == 5) {
                System.out.println("5min");
                List<Tic5iost> listForDB = new ArrayList<>();
                for (int i = 30; i < maxIndex; i++) {
                    Tic5iost tic5 = new Tic5iost();
                    tic5.setRsi(rsi.getValue(i).floatValue());
                    tic5.setMacd(macd.getValue(i).floatValue());
                    tic5.setEmashort(emaShort.getValue(i).floatValue());
                    tic5.setCloseTime(series.getBar(i).getEndTime());
                    listForDB.add(tic5);
                }
                dbConnector.putIndicatorsInDatabaset5Iost(listForDB);
            }
        }
        if (pair.equals(Pairs.FTM_USDT)) {
            if (tic == 5) {
                System.out.println("5min");
                List<Tic5ftm> listForDB = new ArrayList<>();
                for (int i = 30; i < maxIndex; i++) {
                    Tic5ftm tic5 = new Tic5ftm();
                    tic5.setRsi(rsi.getValue(i).floatValue());
                    tic5.setMacd(macd.getValue(i).floatValue());
                    tic5.setEmashort(emaShort.getValue(i).floatValue());
                    tic5.setCloseTime(series.getBar(i).getEndTime());
                    listForDB.add(tic5);
                }
                dbConnector.putIndicatorsInDatabaset5Ftm(listForDB);
            }
        }

    }
}
