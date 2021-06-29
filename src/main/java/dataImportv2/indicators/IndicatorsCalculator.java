package dataImportv2.indicators;

import dataImportv2.indicators.strategy.AionStrategy5m;
import dataImportv2.indicators.strategy.Context;
import dataImportv2.indicators.strategy.NanoStrategy5m;
import enums.Pairs;
import enums.Tics;
import hibernate.entities.Pair_Candle;
import hibernate.entities.tics.Tic;

import java.util.LinkedList;
import java.util.List;

public class IndicatorsCalculator {
    private final List<? extends Pair_Candle> candles;
    private final Tics ticTime;
    private final Pairs pair;
    private Context context;

    public IndicatorsCalculator(List<? extends Pair_Candle> candles, Tics ticTime, Pairs pair) {
        this.candles = candles;
        this.ticTime = ticTime;
        this.pair = pair;
        context = new Context();
    }

    public List<Tic> getTics() {
        List<Tic> indicators = new LinkedList<>();
        switch (pair) {
            case AION_USDT:
                context.setStrategy(new AionStrategy5m());
                indicators = context.create(candles);
                break;
            case NANO_USDT:
                context.setStrategy(new NanoStrategy5m());
                indicators = context.create(candles);
        }
        return indicators;
    }
}
