package triggers.buy;

import hibernate.entities.tics.Tic;

import java.util.List;

public class MacdTriggerBuy {
    public static boolean macdBuy(List<Tic> indicators, int buyRsi, int ticnum) {
        float macdNew = indicators.get(ticnum-1).getMacd();
        float macdMiddle = indicators.get(ticnum - 2).getMacd();
        float macdOld = indicators.get(ticnum - 3).getMacd();
        float macdOldest = indicators.get(ticnum - 4).getMacd();

        return macdOldest > macdOld && macdOld > macdMiddle && macdMiddle < macdNew;

    }
}
