package testbotv2;

import hibernate.entities.tics.Tic;
import tradeTriggers.BuyTrigger;
import tradeTriggers.SellTrigger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TestBot {
    private final List<List<Tic>> indicators;
    int RsiBuy = 35;
    int RsiSell = 69;

    public TestBot(List<List<Tic>> indicators) {
        this.indicators = indicators;
    }

    public void test() {
        boolean tradingNano = false;
        boolean tradingAion = false;
        BigDecimal wallet = BigDecimal.ONE;
        BigDecimal buyPrice = BigDecimal.ZERO;
        BigDecimal profit = BigDecimal.ONE;
        int transaction = 0;
        float buyMacd = 0;

        for (int i = 5; i < indicators.get(0).size(); i++) {

            List<Tic> aion = indicators.get(0);
            List<Tic> nano = indicators.get(1);

            if (!tradingNano && !tradingAion) {
                if (BuyTrigger.buy(aion, RsiBuy, i)) {
                    buyMacd = aion.get(i).getMacd();
                    tradingAion = true;
//                    System.out.println("buy aion");
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    buyPrice = aion.get(i).getPrice();
//                    System.out.println("rsi: " + aion.get(i).getRsi() + " macd: " + aion.get(i).getMacd());
//                    System.out.println("----------------------------");
                } else if (BuyTrigger.buy(nano, RsiBuy, i)) {
                    tradingNano = true;
                    buyMacd = nano.get(i).getMacd();
//                    System.out.println("buy nano");
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    buyPrice = nano.get(i).getPrice();
//                    System.out.println("rsi: " + nano.get(i).getRsi() + " macd: " + nano.get(i).getMacd());
//                    System.out.println("----------------------------");
                }
            }
            if (tradingAion) {
                if (SellTrigger.sell(aion, RsiSell, i, buyMacd) || aion.get(i).getPrice().doubleValue() < buyPrice.floatValue() * 0.97) {
                    profit = aion.get(i).getPrice().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit).multiply(BigDecimal.valueOf(0.999));
                    tradingAion = false;
                    if (profit.doubleValue() < 1) {
                        System.out.println("sell aion");
                        System.out.println(wallet.doubleValue() + " profit" + profit);
                        System.out.println(transaction);
                        System.out.println("rsi: " + aion.get(i).getRsi() + " macd: " + aion.get(i).getMacd());
                        System.out.println("*****************************************************************");
                    }
                    transaction = 0;
                }
            }
            if (tradingNano) {
                if (SellTrigger.sell(nano, RsiSell, i, buyMacd) || nano.get(i).getPrice().doubleValue() < buyPrice.floatValue() * 0.97) {
                    profit = nano.get(i).getPrice().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit).multiply(BigDecimal.valueOf(0.999));
                    tradingNano = false;
                    if (profit.doubleValue() < 1) {
                        System.out.println("sell nano");
                        System.out.println(wallet.doubleValue() + " profit" + profit);
                        System.out.println(transaction);
                        System.out.println("rsi: " + aion.get(i).getRsi() + " macd: " + aion.get(i).getMacd());
                        System.out.println("*****************************************************************");
                    }
                    transaction = 0;
                }

            }
            if (tradingAion || tradingNano) {
                transaction++;
            }
        }

    }
}

