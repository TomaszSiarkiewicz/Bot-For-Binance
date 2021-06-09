package heatmap;

import hibernate.DbConnector;
import hibernate.entities.Pair_Candle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Obosolete traiding strategy
 */

public class HeatMap {
    BigDecimal profit = new BigDecimal("1.005");
    DbConnector dbConnector = new DbConnector();
    List<Pair_Candle> list = dbConnector.getPairCandle30minData();


    public List<Pair_Candle> createHeatmap(List<Pair_Candle> list) {
        List<Pair_Candle> heatMapList = new ArrayList<>();

        BigDecimal minPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = BigDecimal.valueOf(0);
        int currentTicNum = 0;
        int ticsToCheck = 4;
        int maxTime = 0; //10

        while (currentTicNum < list.size() - ticsToCheck - 1) {
            Pair_Candle currentTic = list.get(currentTicNum);
            minPrice = currentTic.getLow();
            maxPrice = currentTic.getHigh();
            for (int i = 1; i < 5; i++) {
                Pair_Candle ticToCheck = list.get(currentTicNum + i);
                if (compare(ticToCheck.getHigh(), maxPrice) ){
                    maxPrice = ticToCheck.getHigh();
                }
            }
            if (compare(maxPrice, minPrice.multiply(profit))){
                heatMapList.add(currentTic);
            }
            currentTicNum++;
        }
        System.out.println("heatmap size = " + heatMapList.size());
        return heatMapList;
    }


    private boolean compare(BigDecimal isThisBigger, BigDecimal thenThis) {
        int result = isThisBigger.compareTo(thenThis);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

}
