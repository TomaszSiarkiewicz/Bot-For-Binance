package triggers;

import hibernate.entities.tics.Tic;
import triggers.sell.MacdTriggerSell;
import triggers.sell.RsiTriggerSell;

import java.math.BigDecimal;
import java.util.List;

/**
 * Using Macd and Rsi indicators returns false if it's time to sell
 */

public class SellTrigger {


    public static boolean sell(List<Tic> indicators, int sellRsi, int ticnum, float macdBuy) {

        return MacdTriggerSell.macdsel(indicators, sellRsi, ticnum, macdBuy)
                && RsiTriggerSell.rsiSell(indicators, sellRsi, ticnum);
    }
}
