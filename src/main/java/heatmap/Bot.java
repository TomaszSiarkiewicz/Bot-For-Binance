package heatmap;

import hibernate.DbConnector;
import hibernate.entities.Pair_Candle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

public class Bot {


    public static HashMap<String, BigDecimal> run(HashMap<LocalTime, Integer> map, BigDecimal stopLoss, BigDecimal takeProfit, int sellTimer, BigDecimal trailingStop) {
        DbConnector dbConnector = new DbConnector();
        BigDecimal wallet = BigDecimal.valueOf(100);
        BigDecimal buyPrice = BigDecimal.valueOf(0);
        BigDecimal currentPrice = BigDecimal.valueOf(0);
        BigDecimal maxPrice = BigDecimal.valueOf(0);
        ZonedDateTime latestSell = ZonedDateTime.now();
        boolean trading = false;
        int buySelTrades = 0;
        BigDecimal takepsell = new BigDecimal(0);
        BigDecimal timerSell = new BigDecimal(0);
        BigDecimal stoplo = new BigDecimal(0);
        BigDecimal previousHigh = new BigDecimal(0);
        BigDecimal stopLossPrice = BigDecimal.valueOf(0);

        List<Pair_Candle> pc = dbConnector.getPairCandle5minBTCData();
        currentPrice = pc.get(0).getOpen();
        for (Pair_Candle pairCandle : pc) {
            currentPrice = pairCandle.getHigh();

            if (map.containsKey(pairCandle.getOpenTime().toLocalTime()) && trading == false) {
                stopLossPrice = buyPrice.multiply(stopLoss);
                buyPrice = pairCandle.getOpen();
                latestSell = pairCandle.getOpenTime().plusMinutes(sellTimer);
                wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                System.out.println("buying");
                trading = true;
                buySelTrades = buySelTrades + 2;
            }

            if (trading) {
                if (compare(pairCandle.getHigh(),buyPrice.multiply(BigDecimal.valueOf(1.007)))) {
                    stopLossPrice = pairCandle.getHigh().multiply(trailingStop);
                }
                if (compare(currentPrice, buyPrice.multiply(takeProfit))) {
                    wallet = (currentPrice.divide(buyPrice, 12, RoundingMode.HALF_UP)).multiply(wallet);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trading = false;
                    System.out.println("take profit");
                    takepsell = takepsell.add(BigDecimal.valueOf(1));
                }
                if (compare(stopLossPrice, currentPrice)) {
                    wallet = (currentPrice.divide(buyPrice, 12, RoundingMode.HALF_UP)).multiply(wallet);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trading = false;
                    stoplo = stoplo.add(BigDecimal.valueOf(1));
                    System.out.println("stop loss");
                }
                if (pairCandle.getCloseTime().isAfter(latestSell)) {
                    wallet = (currentPrice.divide(buyPrice, 12, RoundingMode.HALF_UP)).multiply(wallet);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trading = false;
                    System.out.println("timer sell");
                    timerSell = timerSell.add(BigDecimal.valueOf(1));
                }

            }
            previousHigh = pairCandle.getHigh();

        }

        HashMap<String, BigDecimal> ret = new HashMap<>();
        ret.put("wallet", wallet);
        ret.put("take profit", takepsell);
        ret.put("stop loss", stoplo);
        ret.put("timer sell", timerSell);
        ret.put("trades", BigDecimal.valueOf(buySelTrades));

        return ret;
    }

    public static boolean compare(BigDecimal isfirstBigger, BigDecimal thenSecond) {
//        BigDecimal profit = new BigDecimal("1.01");
//        BigDecimal minWithprofit = thenSecond.multiply(profit);
        int result = isfirstBigger.compareTo(thenSecond);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

}
