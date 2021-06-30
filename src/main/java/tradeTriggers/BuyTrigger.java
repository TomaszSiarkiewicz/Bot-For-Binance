package tradeTriggers;

import hibernate.entities.tics.Tic;
import tradeTriggers.buy.MacdTriggerBuy;
import tradeTriggers.buy.RsiTriggerBuy;

import java.util.List;

/**
 * Returns true if it's time to buy
 */
public class BuyTrigger {

    public static boolean buy(List<Tic> indicators, int buyRsi, int ticnum) {


        return MacdTriggerBuy.macdBuy(indicators ,buyRsi, ticnum)
                || RsiTriggerBuy.rsiBuy(indicators ,buyRsi, ticnum);
    }


}

