package bot;

import enums.Pairs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.*;

public class Trader implements Runnable {
    private final BinanceDataRepository bdr;
    private final CurrentPriceProvider cpp;
    private final WalletInfoProvider wip;
    private final List<Pairs> tradeableCoins;
    private Map<Pairs, Trade> currentlyTrading = new HashMap<>();
    private List<RsiStrategy> allCoinsStrategies;
    private long lastUpdate = ZonedDateTime.now().getNano();
    private boolean run = true;
    private boolean panic = false;
    private List<Trade> trades;
    private volatile BigDecimal walletUsdt = BigDecimal.ONE;
    private volatile BigDecimal invested = BigDecimal.ZERO;


    public Trader() {
        bdr = new BinanceDataRepository();
        cpp = new CurrentPriceProvider(bdr);
        wip = new WalletInfoProvider(bdr);
        tradeableCoins = BotConstant.tradeableCoins;
        allCoinsStrategies = getStrategies(tradeableCoins);
        trades = new LinkedList<>();
        new Thread(wip).start();
    }

    public void run() {
        allCoinsStrategies = getStrategies(tradeableCoins);
        Trade trade = null;
        BigDecimal currentInvest = BigDecimal.ZERO;

        while (run) {
            // iterates trough all coins
            for (RsiStrategy strategy : allCoinsStrategies) {
                // proceeding to buy only if currentlyTrading less then 3 and not trading current pair now
                if (currentlyTrading.size() < 4 && !currentlyTrading.containsKey(strategy.getPair())) {
                    if (strategy.getResult(true).equals("buy")) {
                        //creates new trade
                        trade = new Trade(strategy);
                        currentInvest = getWalletsPart(currentlyTrading, walletUsdt);
                        trade.setAmountInUsdt(currentInvest);
                        currentlyTrading.put(trade.getPair(), trade);
                        invested = invested.add(currentInvest);
                        walletUsdt = walletUsdt.subtract(currentInvest);

                        System.out.println("Buying " + trade.getPair());

                        System.out.println(trade);
                    }
                }
                if (currentlyTrading.size() > 0) {
                    if (strategy.getResult(false).equals("sell") && currentlyTrading.containsKey(strategy.getPair())) {
                        trade = new Trade(strategy, currentlyTrading.get(strategy.getPair()));
                        trades.add(trade);
                        System.out.println("Selling " + strategy.getPair());

                        currentlyTrading.remove(strategy.getPair());

                        currentInvest = trade.getAmountInUsdt().multiply(trade.getProfit());
                        walletUsdt = walletUsdt.add(currentInvest);
                        invested = invested.subtract(trade.getAmountInUsdt());

                        System.out.println(trade);
                        currentlyTrading.remove(strategy.getPair());
                    }
                }

            }

            try {
                Thread.sleep(10000);
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

    private BigDecimal getWalletsPart(Map<Pairs, Trade> currentlyTrading, BigDecimal wallet) {
        if (currentlyTrading.size() == 0) return wallet.divide(BigDecimal.valueOf(4), 5, RoundingMode.HALF_DOWN);
        if (currentlyTrading.size() == 1) return wallet.divide(BigDecimal.valueOf(3), 5, RoundingMode.HALF_DOWN);
        if (currentlyTrading.size() == 2) return wallet.divide(BigDecimal.valueOf(2), 5, RoundingMode.HALF_DOWN);
        if (currentlyTrading.size() == 3) return wallet;
        return BigDecimal.ZERO;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public Map<Pairs, Trade> getCurrentlyTrading() {
        return currentlyTrading;
    }

    public BigDecimal getWalletUsdt() {
        return walletUsdt;
    }

    public BigDecimal getInvested() {
        return invested;
    }
}
