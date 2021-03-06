package tradeTriggers.buy;

import hibernate.entities.tics.Tic;

import java.util.List;

public class RsiTriggerBuy {

    public static boolean rsiBuy(List<Tic> indicators, int buyRsi, int ticnum) {
        float rsi = indicators.get(ticnum).getRsi();
        float previousrsi = indicators.get(ticnum - 2).getRsi();
        if (rsi < buyRsi) {
            return true;
        } else {
            return false;
        }
    }
}
