package bot;

import enums.Pairs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

public class Trader implements Runnable {
    private final BinanceDataRepository bdr;
    private final CurrentPriceProvider cpp;
    private List<Pairs> tradeableCoins;
    private List<RsiStrategy> allCoinsStrategies;
    private long lastUpdate = ZonedDateTime.now().getNano();
    private boolean run = true;
    private boolean panic = false;
    private BigDecimal walletValue = BigDecimal.ONE;
    private List<Trade> trades;

    public Trader() {
        bdr = new BinanceDataRepository();
        cpp = new CurrentPriceProvider(bdr);
        tradeableCoins = BotConstant.tradeableCoins;
        allCoinsStrategies = getStrategies(tradeableCoins);
        trades = new LinkedList<>();
    }

    public void run(){
        allCoinsStrategies = getStrategies(tradeableCoins);
        Trade trade = null;
        Pairs currentlyTrading = null;
        boolean trading = false;

            while (run) {
            for (RsiStrategy strategy : allCoinsStrategies) {
                if (!trading) {
                    if (strategy.getResult().equals("buy")) {
                        trade = new Trade(strategy);
                        currentlyTrading = trade.getPair();
                        trading = true;
                        System.out.println("Buying " + currentlyTrading);
                        walletValue = walletValue.multiply(new BigDecimal("0.999"));
                        System.out.println(trade);
                    }
                }
                if (trading) {
                    if (strategy.getResult().equals("sell") && currentlyTrading.equals(strategy.getPair())) {
                        trade = new Trade(strategy, trade);
                        System.out.println("Selling " + currentlyTrading);
                        currentlyTrading = null;
                        trading = false;
                        trades.add(trade);
                        walletValue = (trade.getSellPrice().divide(trade.getBuyPrice(), 5, RoundingMode.HALF_UP)).multiply(walletValue).multiply(new BigDecimal("0.999"));
                        System.out.println(trade);
                        System.out.println(walletValue);
                    }
                }

            }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    }

    private List<RsiStrategy> getStrategies(List<Pairs> pairs) {
        List<RsiStrategy> strategies = new LinkedList<>();
        for (Pairs pair : pairs) {
            strategies.add(new RsiStrategy(pair, bdr, cpp));
        }
        return strategies;
    }

    public List<RsiStrategy> getAllCoinsStrategies() {
        return allCoinsStrategies;
    }

    private void print(RsiStrategy strategy) {
        String s = "Current values: " + strategy.getPair() +
                " price: " + strategy.getLastIndicator().getPrice() +
                " Rsi: " + strategy.getLastIndicator().getRsi() +
                " Macd: " + strategy.getLastIndicator().getMacd();
        System.out.println(s);
    }

    public List<Trade> getTrades() {
        return trades;
    }
}
