package heatmap;

import hibernate.DbConnector;
import hibernate.entities.Pair_Candle;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeatMapBot {
    public static void startBot(){
        DbConnector dbc = new DbConnector();
        HeatMap heatMap = new HeatMap();

        List<Pair_Candle> list = dbc.getPairCandle15minData();
        List<Pair_Candle> hotList = new ArrayList<Pair_Candle>();

        hotList = heatMap.createHeatmap(list);
        HashMap<LocalTime, Integer> map = besttics(hotList);

        for (Map.Entry<LocalTime, Integer> entry : map.entrySet()) {
            System.out.println("time: " + entry.getKey() + " repeats: " + entry.getValue());
        }
//        for (int i = 0; i < list.size() - 1; i++) {
//            Pair_Candle tic = list.get(i);
//            BigDecimal min = tic.getOpen();
//            BigDecimal max = tic.getHigh();
//            Pair_Candle nextTic = list.get(i + 1);
//
//            if (compare(nextTic.getHigh(), min)) {
//                hotList.add(tic);
//            }
//        }
//        System.out.println(list.size());
//        System.out.println(hotList.size());
//        HashMap<LocalTime, Integer> map = besttics(hotList);
//
//        for (Map.Entry<LocalTime, Integer> entry : map.entrySet()) {
//            System.out.println("time: " + entry.getKey() + " repeats: " + entry.getValue());
//        }
//        System.out.println(map.size());

        System.out.println("-----------------------------");
        printBestValues(map);
//        HashMap<LocalTime, Integer> most = mostOften(map);
//        for (Map.Entry<LocalTime, Integer> entry : most.entrySet()) {
//            System.out.println("time: " + entry.getKey() + " repeats: " + entry.getValue());
//        }
//
//        BigDecimal mala = new BigDecimal(1);
//        BigDecimal duza = new BigDecimal(1.22);
//
//        System.out.println(compare(mala, duza));
//        System.out.println(heatmap.Bot.Bot.compare(duza, mala));
//        System.out.println(mala.divide(duza, 11, RoundingMode.HALF_UP));
//        System.out.println(duza.divide(mala, 11, RoundingMode.HALF_UP));
//        System.out.println(heatmap.Bot.Bot.run(most));
        BigDecimal stopLoss = BigDecimal.valueOf(0.9);
        BigDecimal takeProfit = BigDecimal.valueOf(1.05);
        BigDecimal trailingStop = BigDecimal.valueOf(0.999);
        int timerStop = 550;    //550

        HashMap <String, BigDecimal>result = Bot.run(printBestValues(map), stopLoss, takeProfit, timerStop, trailingStop);

        for (Map.Entry<String, BigDecimal> entry : result.entrySet()) {
            System.out.println(entry.getKey() +":   " + entry.getValue());
        }

    }

    public static HashMap<LocalTime, Integer> printBestValues(HashMap<LocalTime, Integer> map) {
        HashMap<LocalTime, Integer> newMap  = new HashMap<>();
        for (int i = 0; i <4; i++) {
            Map.Entry<LocalTime, Integer> maxEntry = null;
            for (Map.Entry<LocalTime, Integer> entry : map.entrySet()) {
                if (maxEntry == null || entry.getValue()
                        .compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }
//            System.out.println("time : " + maxEntry.getKey() + "     repeats: " + maxEntry.getValue());
            newMap.put(maxEntry.getKey(), maxEntry.getValue());
            map.remove(maxEntry.getKey());
        }
        return newMap;
    }

    public static HashMap<LocalTime, Integer> mostOften(HashMap<LocalTime, Integer> map) {
        HashMap<LocalTime, Integer> hashMap = new HashMap();
        LocalTime key1 = LocalTime.of(1, 11);
        LocalTime key2 = LocalTime.of(1, 11);
        LocalTime key3 = LocalTime.of(1, 11);
        LocalTime key4 = LocalTime.of(1, 11);
        LocalTime key5 = LocalTime.of(1, 11);
        int val1 = 0;
        int val2 = 0;
        int val3 = 0;
        int val4 = 0;
        int val5 = 0;
        for (Map.Entry<LocalTime, Integer> entry : map.entrySet()) {
            if (entry.getValue() > val1) {
                val5 = val4;
                key5 = key4;
                val4 = val3;
                key4 = key3;
                val3 = val2;
                key3 = key2;
                val2 = val1;
                key2 = key1;
                val1 = entry.getValue();
                key1 = entry.getKey();
            } else if (entry.getValue() > val2) {
                val5 = val4;
                key5 = key4;
                val4 = val3;
                key4 = key3;
                val3 = val2;
                key3 = key2;
                val2 = entry.getValue();
                key2 = entry.getKey();
            } else if (entry.getValue() > val3) {
                val5 = val4;
                key5 = key4;
                val4 = val3;
                key4 = key3;
                val3 = entry.getValue();
                key3 = entry.getKey();
            } else if (entry.getValue() > val4) {
                val5 = val4;
                key5 = key4;
                val4 = entry.getValue();
                key4 = entry.getKey();
            } else if (entry.getValue() > val5) {
                val5 = entry.getValue();
                key5 = entry.getKey();
            }
        }
        hashMap.put(key1, val1);
        hashMap.put(key5, val5);
        hashMap.put(key4, val4);
        hashMap.put(key3, val3);
        hashMap.put(key2, val2);
        return hashMap;
    }

    public static HashMap<LocalTime, Integer> besttics(List<Pair_Candle> list) {
        HashMap<LocalTime, Integer> hashMap = new HashMap();
        for (Pair_Candle tic : list) {
            LocalTime time = tic.getOpenTime().toLocalTime();
            if (!hashMap.containsKey(time)) {
                hashMap.put(time, 1);
            } else {
                hashMap.put(time, hashMap.get(time) + 1);
            }
        }


        return hashMap;
    }

    public static boolean compare(BigDecimal isThisBigger, BigDecimal thenThis) {
//        BigDecimal profit = new BigDecimal("1.01");
//        BigDecimal minWithprofit = thenThis.multiply(profit);
        int result = isThisBigger.compareTo(thenThis);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}
