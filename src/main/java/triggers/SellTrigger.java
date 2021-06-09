package triggers;

import hibernate.entities.tics.Tic;
import triggers.sell.MacdTriggerSell;
import triggers.sell.RsiTriggerSell;

import java.util.List;

/**
 * Using Macd and Rsi indicators returns false if it's time to sell
 */

public class SellTrigger {


    public static boolean sell(List<Tic> indicators, int sellRsi, int ticnum) {

        return MacdTriggerSell.macdsel(indicators, sellRsi, ticnum) && RsiTriggerSell.rsiSell(indicators, sellRsi, ticnum);
    }
}
