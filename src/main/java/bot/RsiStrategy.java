package bot;

import enums.Pairs;
import triggers.BuyTrigger;
import triggers.SellTrigger;

public class RsiStrategy {
    private final IndicatorsProvider indicatorsProvider;
    private final Pairs pair;
    private String result = null;


    public RsiStrategy(Pairs pair, BinanceDataRepository binanceDataRepository, CurrentPriceProvider currentPriceProvider) {
        indicatorsProvider = new IndicatorsProvider(pair, binanceDataRepository, currentPriceProvider);
        this.pair = pair;
    }

    private void decisionMaker() {
        System.out.print("rsi = " + getRsiValue());
        int lastTic = indicatorsProvider.getIndicators().size() - 1;
        if (BuyTrigger.buy(indicatorsProvider.getIndicators(), BotConstant.RSI_BUY, lastTic)) {
            result = "buy";
        } else if (!SellTrigger.sell(indicatorsProvider.getIndicators(), BotConstant.RSI_SELL, lastTic)) {
            result = "sell";
        } else {
            result = null;
        }
    }

    public Pairs getPair() {
        return pair;
    }

    public float getRsiValue() {
        return indicatorsProvider.getIndicators().get(indicatorsProvider.getIndicators().size() - 1).getRsi();
    }

    public String getResult() {
        decisionMaker();
        return result;
    }


}
