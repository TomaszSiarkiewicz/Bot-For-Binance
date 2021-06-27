package macdMtmBot;

import hibernate.entities.Pair_Candle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

public class MacdBot {

    public static BotResult start(HashMap<ZonedDateTime, Boolean> mapAion, HashMap<ZonedDateTime, Boolean> mapNano, List<List<Pair_Candle>> lists) {
        int successTrades = 0;
        int trades = 0;
        int AIONtrades = 0;
        int NANOtrades = 0;
        BotResult botResult1 = null;

        BigDecimal wallet = new BigDecimal("1");

        BigDecimal buyPrice = new BigDecimal(0);
        BigDecimal profit = new BigDecimal(0);

        List<Pair_Candle> aion = lists.get(0);
        List<Pair_Candle> nano = lists.get(1);


        boolean tradingAion = false;
        boolean tradingNano = false;

        ZonedDateTime time = aion.get(0).getCloseTime();
        System.out.println(time.getDayOfMonth() + " " + time.getHour() + " " + time.getMinute());
        for (int i = 0; i < aion.size(); i++) {
            Pair_Candle pcAion = aion.get(i);
            Pair_Candle pcNano = nano.get(i);


            time = pcAion.getCloseTime();


            //            aion
            if (mapAion.containsKey(time) && !tradingAion && !tradingNano) {
                if (mapAion.get(time) && !tradingNano) {
                    buyPrice = pcAion.getClose();
                    tradingAion = true;
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    System.out.println("trade aion");
                }
                if (tradingAion && !mapAion.get(time)) {
                    profit = pcAion.getClose().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trades++;
                    tradingAion = false;
                    if (pcAion.getClose().compareTo(buyPrice.multiply(BigDecimal.valueOf(0.999))) > 0) {
                        successTrades++;
                    }
                    AIONtrades++;
                    System.out.println("wallet: " + wallet + " || profit: " + profit);
                }
            }

            //          nano
            if (mapNano.containsKey(time) && !tradingNano && !tradingAion) {
                if (mapNano.get(time) && !tradingAion) {
                    buyPrice = pcNano.getClose();
                    tradingNano = true;
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    System.out.println("trade nano");
                }
                if (tradingNano && !mapNano.get(time)) {
                    profit = pcNano.getClose().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trades++;
                    tradingNano = false;
                    if (pcNano.getClose().compareTo(buyPrice.multiply(BigDecimal.valueOf(0.999))) > 0) {
                        successTrades++;
                    }
                    NANOtrades++;
                    System.out.println("wallet: " + wallet + " || profit: " + profit);
                }
            }


            wallet = wallet.divide(BigDecimal.valueOf(1), 4, RoundingMode.HALF_UP);
            BotResult botResult = new BotResult();
            botResult.setTrades(trades);
            botResult.setWallet(wallet);
            botResult.setSuccessTrades(successTrades);
            botResult.setAIONtrades(AIONtrades);
            botResult.setNANOtrades(NANOtrades);
            botResult1 = botResult;


        }
        return botResult1;
    }
}