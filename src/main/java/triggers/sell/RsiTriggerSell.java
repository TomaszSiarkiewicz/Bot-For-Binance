package triggers.sell;

import hibernate.entities.tics.Tic;

import java.util.List;

public class RsiTriggerSell {
    public static boolean rsiSell(List<Tic> indicators, int sellRsi, int ticnum) {
        float rsi = indicators.get(ticnum).getRsi();
        float previousrsi = indicators.get(ticnum - 2).getRsi();
        return rsi > sellRsi;
    }
}
