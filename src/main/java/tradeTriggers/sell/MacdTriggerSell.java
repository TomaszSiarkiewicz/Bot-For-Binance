package tradeTriggers.sell;

import hibernate.entities.tics.Tic;

import java.util.List;

public class MacdTriggerSell {
    public static boolean macdsel(List<Tic> indicators, int buyRsi, int ticnum, float macdBuy) {

        float macdNew = indicators.get(ticnum-1).getMacd();
        float macdMiddle = indicators.get(ticnum - 2).getMacd();
        float macdOld = indicators.get(ticnum - 3).getMacd();
        float macdOldest = indicators.get(ticnum - 4).getMacd();

        if (macdBuy < macdNew) {
            return macdOldest < macdOld && macdOld < macdMiddle && macdMiddle > macdNew;
        }else {
            return false;
        }
    }
}
