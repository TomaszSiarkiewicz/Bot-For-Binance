package dataImportv2.indicators.strategy;

import hibernate.entities.Pair_Candle;
import hibernate.entities.tics.Tic;
import hibernate.entities.tics.Tic5nano;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


public class NanoStrategy5m implements IndicatorStrategy {
    int rsiBarCount = 30;

    @Override
    public List<Tic> create(List<? extends Pair_Candle> candles) {


        BarSeries series = new BaseBarSeriesBuilder().build();
        for (Pair_Candle pc : candles) {
            series.addBar(ZonedDateTime.ofInstant((Instant.ofEpochMilli(pc.getCloseTime())), ZoneId.systemDefault()), pc.getOpen(), pc.getHigh(), pc.getLow(), pc.getClose(), pc.getVolume());
        }

        ClosePriceIndicator indicator = new ClosePriceIndicator(series);
        RSIIndicator rsi = new RSIIndicator(indicator, rsiBarCount);
        MACDIndicator macd = new MACDIndicator(rsi);
        EMAIndicator emaShort = new EMAIndicator(rsi, 9);
        int maxIndex = indicator.getBarSeries().getEndIndex();

        List<Tic> listForDB = new ArrayList<>();
        for (int i = 30; i < maxIndex; i++) {
            Tic tic5 = new Tic5nano();
            tic5.setRsi(rsi.getValue(i).floatValue());
            tic5.setMacd(macd.getValue(i).floatValue());
            tic5.setEmashort(emaShort.getValue(i).floatValue());
            tic5.setCloseTime(series.getBar(i).getEndTime());
            tic5.setPrice(BigDecimal.valueOf(series.getBar(i).getClosePrice().floatValue()));
            listForDB.add(tic5);
        }
        return listForDB;
    }
}
