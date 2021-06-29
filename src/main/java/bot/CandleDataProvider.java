package bot;

import api.client.model.market.Candlestick;
import enums.Pairs;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

public class CandleDataProvider implements Runnable {

    private final Pairs pair;
    private final BinanceDataRepository binanceDataRepository;
    private volatile List<Candlestick> candles;
    private boolean run = true;
    private LocalDateTime nextUpdate;


    public CandleDataProvider(Pairs pair, BinanceDataRepository binanceDataRepository) {
        this.pair = pair;
        this.binanceDataRepository = binanceDataRepository;
        init();
    }

    @Override
    public void run() {
        while (run) {
            if (LocalDateTime.now().isAfter(nextUpdate)) {
                System.out.println(" ----CANDLE UPDATE---- ");
                candles = binanceDataRepository.updatePairCandles(pair);
                setNextUpdate();
            }
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

    private void setNextUpdate() {
        long previousUpdate = candles.get(candles.size() - 2).getCloseTime();
        nextUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(previousUpdate), TimeZone
                .getDefault().toZoneId()).plusMinutes(10);

    }

    private void init() {
        candles = binanceDataRepository.updatePairCandles(pair);
        setNextUpdate();
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
