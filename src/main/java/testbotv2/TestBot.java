package testbotv2;

import hibernate.entities.tics.Tic;
import triggers.BuyTrigger;
import triggers.SellTrigger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TestBot {
    private final List<List<Tic>> indicators;
    int RsiBuy = 30;
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
        for (int i = 5; i < indicators.get(0).size(); i++) {
            List<Tic> aion = indicators.get(0);
            List<Tic> nano = indicators.get(1);

            if (!tradingNano && !tradingAion) {
//                if (BuyTrigger.buy(aion, RsiBuy, i)) {
//                    tradingAion = true;
//                    System.out.println("buy aion");
//                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
//                    buyPrice = aion.get(i).getPrice();
//
//                } else
                    if (BuyTrigger.buy(nano, RsiBuy, i)) {
                    tradingNano = true;
                    System.out.println("buy nano");
                    wallet = wallet.multiply(BigDecimal.valueOf(0.999));
                    buyPrice = nano.get(i).getPrice();
                }
            }
            if (tradingNano || tradingAion) {
                if (tradingNano && SellTrigger.sell(nano, RsiSell, i)) {
                    profit = nano.get(i).getPrice().divide(buyPrice, 10, RoundingMode.HALF_UP);
                    wallet = wallet.multiply(profit).multiply(BigDecimal.valueOf(0.999));
                    tradingNano = false;
                    System.out.println("sell nano");
                    System.out.println(wallet.doubleValue());
                    System.out.println(transaction);
                    transaction = 0;
                }
//                if (tradingAion && SellTrigger.sell(nano, RsiSell, i)) {
//                    profit = aion.get(i).getPrice().divide(buyPrice, 10, RoundingMode.HALF_UP);
//                    wallet = wallet.multiply(profit).multiply(BigDecimal.valueOf(0.999));
//                    tradingAion = false;
//                    System.out.println("sell aion");
//                    System.out.println(wallet.doubleValue());
//                    System.out.println(transaction);
//                    transaction = 0;
//                }
            }
            if (tradingAion || tradingNano){
                transaction++;
            }

        }
    }
}
