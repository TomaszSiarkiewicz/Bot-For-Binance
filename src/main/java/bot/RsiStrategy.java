package bot;

import enums.Pairs;
import triggers.BuyTrigger;
import triggers.SellTrigger;

public class RsiStrategy {
    private final IndicatorsProvider indicatorsProvider;
    private String result = null;


    public RsiStrategy(Pairs pair, BinanceDataRepository binanceDataRepository, CurrentPriceProvider currentPriceProvider) {
        indicatorsProvider = new IndicatorsProvider(pair, binanceDataRepository, currentPriceProvider);
    }

    private void decisionMaker() {
        int lastTic = indicatorsProvider.getIndicators().size() - 1;
        if (BuyTrigger.buy(indicatorsProvider.getIndicators(), BotStrategyConstant.RSI_BUY, lastTic)) {
            result = "buy";
        } else if (SellTrigger.sell(indicatorsProvider.getIndicators(), BotStrategyConstant.RSI_SELL, lastTic)) {
            result = "sell";
        } else {
            result = null;
        }
    }
    public String getResult(){
        decisionMaker();
        return result;
    }


}
