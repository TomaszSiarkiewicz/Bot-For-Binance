package dataImportv2.indicators.strategy;

import enums.Pairs;
import hibernate.entities.Pair_Candle;
import hibernate.entities.tics.Tic;

import java.util.List;

public interface IndicatorStrategy {
    List<Tic> create(List<? extends Pair_Candle> candles);
}
