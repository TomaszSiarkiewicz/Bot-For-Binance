package macdMtmBot;

import hibernate.entities.Pair_Candle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

public class MacdBot {

    public static BotResult start(HashMap<ZonedDateTime, Boolean> mapFtm, HashMap<ZonedDateTime, Boolean> mapAion, HashMap<ZonedDateTime, Boolean> mapPsg, HashMap<ZonedDateTime, Boolean> mapIost, HashMap<ZonedDateTime, Boolean> mapNano, HashMap<ZonedDateTime, Boolean> mapShib, List<List<Pair_Candle>> lists) {
        int successTrades = 0;
        int trades = 0;
        int IOSTtrades = 0;
        int AIONtrades = 0;
        int PSGtrades = 0;
        int FTMtrades = 0;
        int NANOtrades = 0;

        BigDecimal wallet = new BigDecimal("1");

        BigDecimal buyPrice = new BigDecimal(0);
        BigDecimal profit = new BigDecimal(0);
        BigDecimal stoploss = new BigDecimal(0);

        List<Pair_Candle> aion = lists.get(0);
        List<Pair_Candle> btc = lists.get(1);
        List<Pair_Candle> psg = lists.get(2);
        List<Pair_Candle> iost = lists.get(3);
        List<Pair_Candle> nano = lists.get(4);
        List<Pair_Candle> shib = lists.get(5);

        boolean tradingAion = false;
        boolean tradingFtm = false;
        boolean tradingPsg = false;
        boolean tradingIost = false;
        boolean tradingNano = false;

        ZonedDateTime time = btc.get(0).getCloseTime();
        System.out.println(time.getDayOfMonth() + " " + time.getHour() + " " + time.getMinute());
        for (int i = 0; i < iost.size(); i++) {
            Pair_Candle pcAion = aion.get(i);
            Pair_Candle pcBtc = btc.get(i);
            Pair_Candle pcPsg = psg.get(i);
            Pair_Candle pcIost = iost.get(i);
            Pair_Candle pcNano = nano.get(i);

            time = pcAion.getCloseTime();


//            TODO stoploss
/*
            if (tradingAion) {
                if (0 > (pcAion.getClose().compareTo(buyPrice.multiply(stoploss)))) {
                    System.out.println(buyPrice.multiply(stoploss).compareTo(pcAion.getClose()));
                    System.out.println("buy price "+ buyPrice+ " sell price"+ pcAion.getClose());
                    profit = pcAion.getClose().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trades++;
                    tradingAion = false;
                    if (pcAion.getClose().compareTo(buyPrice.multiply(BigDecimal.valueOf(0.999))) > 0) {
                        successTrades++;
                    }
                    AIONtrades++;
                    System.out.println(profit);
                }
            }
            if (tradingIost) {
                if (0 < (buyPrice.multiply(stoploss)).compareTo(pcIost.getClose())) {
                    profit = pcIost.getClose().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trades++;
                    tradingIost = false;
                    if (pcIost.getClose().compareTo(buyPrice.multiply(BigDecimal.valueOf(0.999))) > 0) {
                        successTrades++;
                    }
                    IOSTtrades++;
                    System.out.println(profit);
                }
            }
            if (tradingPsg) {
                if (0 < (buyPrice.multiply(stoploss)).compareTo(pcPsg.getClose())) {
                    profit = pcPsg.getClose().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trades++;
                    tradingPsg = false;
                    if (pcPsg.getClose().compareTo(buyPrice.multiply(BigDecimal.valueOf(0.999))) > 0) {
                        successTrades++;
                    }
                    PSGtrades++;
                    System.out.println(profit);
                }
            }
            if (tradingNano) {
                if (0 < (buyPrice.multiply(stoploss)).compareTo(pcAion.getClose())) {
                    profit = pcNano.getClose().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trades++;
                    tradingNano = false;
                    if (pcNano.getClose().compareTo(buyPrice.multiply(BigDecimal.valueOf(0.999))) > 0) {
                        successTrades++;
                    }
                    NANOtrades++;
                    System.out.println(profit);
                }
            }


 */


//                        iost
            if (mapIost.containsKey(time)) {
                if (mapIost.get(time) == true && !tradingFtm && !tradingAion && !tradingPsg && !tradingNano) {
                    buyPrice = pcIost.getClose();
                    tradingIost = true;
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    System.out.println("trade iost");
                }
                if (tradingIost && !mapIost.get(time)) {
                    profit = pcIost.getClose().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trades++;
                    tradingIost = false;
                    if (pcIost.getClose().compareTo(buyPrice.multiply(BigDecimal.valueOf(0.999))) > 0) {
                        successTrades++;
                    }
                    IOSTtrades++;
                    System.out.println(profit);
                }
            }


            //            aion
            if (mapAion.containsKey(time)) {
                if (mapAion.get(time) == true && !tradingFtm && !tradingPsg && !tradingIost && !tradingNano) {
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
                    System.out.println(profit);
                }
            }


            //            psg
            if (mapPsg.containsKey(time)) {
                if (mapPsg.get(time) == true && !tradingFtm && !tradingAion && !tradingIost && !tradingNano) {
                    buyPrice = pcPsg.getClose();
                    tradingPsg = true;
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    System.out.println("trade psg");
                }
                if (tradingPsg && !mapPsg.get(time)) {
                    profit = pcPsg.getClose().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trades++;
                    tradingPsg = false;
                    if (pcPsg.getClose().compareTo(buyPrice.multiply(BigDecimal.valueOf(0.999))) > 0) {
                        successTrades++;
                    }
                    PSGtrades++;
                    System.out.println(profit);
                }
            }

            //          nano
            if (mapNano.containsKey(time)) {
                if (mapNano.get(time) == true && !tradingFtm && !tradingAion && !tradingIost && !tradingPsg) {
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
                    System.out.println(profit);
                }
            }

            // ftm
            if (mapFtm.containsKey(time)) {
                if (mapFtm.get(time) == true && !tradingAion && !tradingPsg && !tradingIost && !tradingNano) {
                    buyPrice = pcBtc.getClose();
                    tradingFtm = true;
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    System.out.println("trade FTM");
                }
                if (tradingFtm && !mapFtm.get(time)) {
                    profit = pcBtc.getClose().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit);
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    trades++;
                    tradingFtm = false;
                    if (pcBtc.getClose().compareTo(buyPrice.multiply(BigDecimal.valueOf(0.999))) > 0) {
                        successTrades++;
                    }
                    FTMtrades++;
                    System.out.println(profit);
                }
            }
        }


        wallet = wallet.divide(BigDecimal.valueOf(1), 4, RoundingMode.HALF_UP);
        BotResult botResult = new BotResult();
        botResult.setTrades(trades);
        botResult.setWallet(wallet);
        botResult.setSuccessTrades(successTrades);
        botResult.setAIONtrades(AIONtrades);
        botResult.setFTMtrades(FTMtrades);
        botResult.setIOSTtrades(IOSTtrades);
        botResult.setPSGtrades(PSGtrades);
        botResult.setNANOtrades(NANOtrades);

        return botResult;

    }
}