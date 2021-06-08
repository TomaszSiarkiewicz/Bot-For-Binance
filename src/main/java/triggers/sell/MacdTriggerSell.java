package triggers.sell;

import hibernate.entities.tics.Tic;

import java.util.List;

public class MacdTriggerSell {
    public static boolean macdsel(List<Tic> indicators, int buyRsi, int ticnum) {
        float macdNew = indicators.get(ticnum).getMacd();
        float macdOld = indicators.get(ticnum-1).getMacd();

        return macdNew < macdOld;
    }
}
