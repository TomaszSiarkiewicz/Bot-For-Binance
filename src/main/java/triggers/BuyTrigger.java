package triggers;

import hibernate.entities.tics.Tic;
import triggers.buy.MacdTriggerBuy;
import triggers.buy.RsiTriggerBuy;

import java.util.List;

public class BuyTrigger {

    public static boolean buy(List<Tic> indicators, int buyRsi, int ticnum) {

        return MacdTriggerBuy.macdBuy(indicators, buyRsi, ticnum) || RsiTriggerBuy.rsiBuy(indicators, buyRsi, ticnum);
    }


}

