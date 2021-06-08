package triggers;

import hibernate.entities.tics.Tic;


import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

public class BuySellMapCreator {


    public static HashMap<ZonedDateTime, Boolean> create(List<Tic> indicators, int sellRsi, int buyRsi) {
        HashMap<ZonedDateTime, Boolean> buySellMap = new HashMap<>();
        boolean trading = false;

        for (int i = 28; i < indicators.size(); i++) {
            if (!trading && BuyTrigger.buy(indicators, buyRsi, i)) {
                buySellMap.put(indicators.get(i).getCloseTime(), true);
                trading = true;
            }
            if (trading && SellTrigger.sell(indicators, sellRsi, i)) {
                buySellMap.put(indicators.get(i).getCloseTime(), false);
                trading = false;
            }
        }
        return buySellMap;
    }

}
