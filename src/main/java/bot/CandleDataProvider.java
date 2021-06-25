package bot;

import api.client.model.market.Candlestick;
import enums.Pairs;

import java.util.List;

public class CandleDataProvider implements Runnable {

    private final Pairs pair;
    private final BinanceDataRepository binanceDataRepository;
    private volatile List<Candlestick> candles;
    private boolean run = true;


    public CandleDataProvider(Pairs pair, BinanceDataRepository binanceDataRepository) {
        this.pair = pair;
        this.binanceDataRepository = binanceDataRepository;
        candles = binanceDataRepository.updatePairCandles(pair);
    }

    @Override
    public void run() {
        while (run) {
            System.out.println(" ----CANDLE UPDATE---- ");
            candles = binanceDataRepository.updatePairCandles(pair);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Candlestick> getRecentCandles() {
        return candles;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
