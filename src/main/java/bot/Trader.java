package bot;

import enums.Pairs;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

public class Trader {
    private final BinanceDataRepository bdr;
    private final CurrentPriceProvider cpp;
    private List<Pairs> tradeableCoins;
    private List<RsiStrategy> allCoinsStrategies;
    private long lastUpdate = ZonedDateTime.now().getNano();
    private boolean run = false;
    private boolean panic = false;

    public Trader() {
        bdr = new BinanceDataRepository();
        cpp = new CurrentPriceProvider(bdr);
        tradeableCoins = BotConstant.tradeableCoins;
        allCoinsStrategies = getStrategies(tradeableCoins);
    }

    public void trade() {
        while (run) {
            for (Pairs pair : tradeableCoins) {
            }
        }
    }

    private List<RsiStrategy> getStrategies(List<Pairs> pairs) {
        List<RsiStrategy> strategies = new LinkedList<>();
        for (Pairs pair : pairs) {
            strategies.add(new RsiStrategy(pair, bdr, cpp));
        }
        return  strategies;
    }

    public List<RsiStrategy> getAllCoinsStrategies() {
        return allCoinsStrategies;
    }
}
