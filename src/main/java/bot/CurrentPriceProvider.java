package bot;

import api.client.model.market.SymbolPrice;
import enums.Pairs;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CurrentPriceProvider implements Runnable {

    private final Pairs pair;
    private final BinanceDataRepository binanceDataRepository;
    private volatile List<SymbolPrice> symbolPrice;
    private boolean run = true;

    public CurrentPriceProvider(Pairs pair, BinanceDataRepository binanceDataRepository) {
        this.binanceDataRepository = binanceDataRepository;
        this.pair = pair;
        init();
    }

    @Override
    public void run() {
        while (run) {
            System.out.println(" -----PRICE UPDATE----- ");
            symbolPrice = binanceDataRepository.currentPrices();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void init() {
        symbolPrice = binanceDataRepository.currentPrices();
    }

    public BigDecimal getPrice(Pairs pair) {
        return symbolPrice.stream().filter(i -> i.getSymbol().equals(pair.getName())).collect(Collectors.toList()).get(0).getPrice();
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
