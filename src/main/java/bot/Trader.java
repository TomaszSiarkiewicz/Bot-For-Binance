package bot;

import enums.Pairs;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Trader {
    private final BinanceDataRepository bdr;
    private final CurrentPriceProvider cpp;
    private List<Pairs> tradeableCoins;
    private List<RsiStrategy> allCoinsStrategies;
    private long lastUpdate = ZonedDateTime.now().getNano();
    private boolean run = true;
    private boolean panic = false;

    public Trader() {
        bdr = new BinanceDataRepository();
        cpp = new CurrentPriceProvider(bdr);
        tradeableCoins = BotConstant.tradeableCoins;
        allCoinsStrategies = getStrategies(tradeableCoins);
    }

    public void trade() throws InterruptedException {
        while (run) {
            allCoinsStrategies = getStrategies(tradeableCoins);
            for (Pairs pair : tradeableCoins) {
                System.out.println(pair.getName() + " " + allCoinsStrategies.stream().filter(i -> i.getPair().equals(pair)).collect(Collectors.toList()).get(0).getResult());
            }
            Thread.sleep(1500);
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
}
