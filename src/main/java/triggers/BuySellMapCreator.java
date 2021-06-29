package triggers;

import hibernate.entities.tics.Tic;


import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * Creates buy/sell map for MacdMtmBot
 *
 * @Param  sellRsi  suggested values: 60-80
 * @Param  buyRsi  suggested values: 25-40
 *
 */

public class BuySellMapCreator {


    public static HashMap<ZonedDateTime, Boolean> create(List<Tic> indicators, int sellRsi, int buyRsi) {
        HashMap<ZonedDateTime, Boolean> buySellMap = new HashMap<>();

//        for (int i = 28; i < indicators.size(); i++) {
//            if (BuyTrigger.buy(indicators, buyRsi, i)) {
//                buySellMap.put(indicators.get(i).getCloseTime(), true);
//            }
//            if (SellTrigger.sell(indicators, sellRsi, i)) {
//                buySellMap.put(indicators.get(i).getCloseTime(), false);
//
//            }
//        }
        return buySellMap;
    }

}
