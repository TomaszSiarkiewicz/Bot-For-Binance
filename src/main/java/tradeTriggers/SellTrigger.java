package tradeTriggers;

import hibernate.entities.tics.Tic;
import tradeTriggers.sell.MacdTriggerSell;
import tradeTriggers.sell.RsiTriggerSell;

import java.util.List;

/**
 * Using Macd and Rsi indicators returns false if it's time to sell
 */

public class SellTrigger {


    public static boolean sell(List<Tic> indicators, int sellRsi, int ticnum, float macdBuy) {

        return MacdTriggerSell.macdsel(indicators, sellRsi, ticnum, macdBuy)
                || RsiTriggerSell.rsiSell(indicators, sellRsi, ticnum);
    }
}
