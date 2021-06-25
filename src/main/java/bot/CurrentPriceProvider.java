package bot;

import api.client.model.market.SymbolPrice;
import enums.Pairs;

import java.math.BigDecimal;

public class CurrentPriceProvider implements Runnable {

    private final Pairs pair;
    private final BinanceDataRepository binanceDataRepository;
    private volatile SymbolPrice symbolPrice;
    private boolean run = true;

    public CurrentPriceProvider(Pairs pair, BinanceDataRepository binanceDataRepository) {
        this.binanceDataRepository = binanceDataRepository;
        this.pair = pair;
    }

    @Override
    public void run() {
        while (run) {
            System.out.println(" -----PRICE UPDATE----- ");
            symbolPrice = binanceDataRepository.currentPrice(pair);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public BigDecimal getPrice() {
        return symbolPrice.getPrice();
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
