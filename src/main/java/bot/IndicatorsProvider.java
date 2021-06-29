package bot;

import api.client.model.market.Candlestick;
import enums.Pairs;
import hibernate.entities.tics.Tic;

import java.util.List;

public class IndicatorsProvider {
    private final BinanceDataRepository binanceDataRepository;
    private final CandleDataProvider recentCandles;
    private final CurrentPriceProvider recentPrice;
    private final Pairs pair;
    List<Tic> indicators;

    public IndicatorsProvider(Pairs pair, BinanceDataRepository dataRepository, CurrentPriceProvider currentPriceProvider) {
        this.pair = pair;
        binanceDataRepository = dataRepository;
        recentCandles = new CandleDataProvider(pair, dataRepository);
        recentPrice = currentPriceProvider;
        Thread threadRecentCandle = new Thread(recentCandles);
        threadRecentCandle.start();
        indicators = initData(pair, dataRepository);

    }

    public List<Tic> getIndicators() {
        List<Candlestick> candlesticks = recentCandles.getRecentCandles();
        candlesticks.get(candlesticks.size() - 1).setClose(recentPrice.getPrice(pair));
        return binanceDataRepository.getIndicators(candlesticks);
    }

    private List<Tic> initData(Pairs pair, BinanceDataRepository dataRepository) {
        return dataRepository.getIndicators(dataRepository.updatePairCandles(pair));
    }
}
