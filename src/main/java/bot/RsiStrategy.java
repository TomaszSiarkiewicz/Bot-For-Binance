package bot;

import enums.Pairs;
import hibernate.entities.tics.Tic;
import tradeTriggers.BuyTrigger;
import tradeTriggers.SellTrigger;

public class RsiStrategy {
    private final IndicatorsProvider indicatorsProvider;
    private final Pairs pair;
    private String result = null;
    private float macdBuy = 0;


    public RsiStrategy(Pairs pair, BinanceDataRepository binanceDataRepository, CurrentPriceProvider currentPriceProvider) {
        indicatorsProvider = new IndicatorsProvider(pair, binanceDataRepository, currentPriceProvider);
        this.pair = pair;
    }

    private void decisionMaker(boolean isBuyDecision) {
        result = "null";
        int lastTic = indicatorsProvider.getIndicators().size() - 1;
        if (isBuyDecision) {
            if (BuyTrigger.buy(indicatorsProvider.getIndicators(), BotConstant.RSI_BUY, lastTic)) {
                macdBuy = indicatorsProvider.getIndicators().get(lastTic).getMacd();
                result = "buy";
            }
        } else {
            if (SellTrigger.sell(indicatorsProvider.getIndicators(), BotConstant.RSI_SELL, lastTic, macdBuy)) {
                result = "sell";
            }
        }
    }

    public Pairs getPair() {
        return pair;
    }

    public float getRsiValue() {
        return indicatorsProvider.getIndicators().get(indicatorsProvider.getIndicators().size() - 1).getRsi();
    }

    public String getResult(boolean isBuyDecision) {
        decisionMaker(isBuyDecision);
        return result;
    }

    public float getMacdBuy() {
        return macdBuy;
    }

    public Tic getLastIndicator() {
        return indicatorsProvider.getIndicators().get(indicatorsProvider.getIndicators().size() - 1);
    }

    public IndicatorsProvider getIndicatorsProvider() {
        return indicatorsProvider;
    }

    @Override
    public String toString() {
        return "RsiStrategy{" +
                "indicatorsProvider=" + indicatorsProvider +
                ", pair=" + pair +
                ", result='" + result + '\'' +
                ", macdBuy=" + macdBuy +
                '}';
    }
}
