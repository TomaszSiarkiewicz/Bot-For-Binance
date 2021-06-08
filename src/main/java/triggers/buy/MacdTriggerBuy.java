package triggers.buy;

import hibernate.entities.tics.Tic;

import java.util.List;

public class MacdTriggerBuy {
    public static boolean macdBuy(List<Tic> indicators, int buyRsi, int ticnum){
        float macdNew = indicators.get(ticnum).getMacd();
        float macdOld = indicators.get(ticnum-1).getMacd();

        return macdNew > macdOld;
    }
}
