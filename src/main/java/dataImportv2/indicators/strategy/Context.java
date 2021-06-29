package dataImportv2.indicators.strategy;

import hibernate.entities.Pair_Candle;
import hibernate.entities.tics.Tic;

import java.util.List;

public class Context {
    private IndicatorStrategy strategy;


    public void setStrategy(IndicatorStrategy strategy) {
        this.strategy = strategy;
    }
    public List<Tic> create(List<? extends Pair_Candle> candles){
        return strategy.create(candles);
    }

}
