package bot;

import api.client.model.market.Candlestick;
import enums.Pairs;
import hibernate.entities.tics.Tic;

import java.util.List;

public class IndicatorsProvider {
    private final BinanceDataRepository binanceDataRepository;
    private final CandleDataProvider recentCandles;
    private final CurrentPriceProvider recentPrice;
    List<Tic> indicators;

    public IndicatorsProvider(Pairs pair, BinanceDataRepository dataRepository) {
        binanceDataRepository = dataRepository;
        recentCandles = new CandleDataProvider(pair, dataRepository);
        recentPrice = new CurrentPriceProvider(pair, dataRepository);
        Thread threadRecentCandle = new Thread(recentCandles);
        Thread threadRecentPrice = new Thread(recentPrice);
        threadRecentCandle.start();
        threadRecentPrice.start();
        indicators = initData(pair, dataRepository);
    }

    public List<Tic> getIndicators(Pairs pair) {
        List<Candlestick> candlesticks = recentCandles.getRecentCandles();
        candlesticks.get(candlesticks.size() - 1).setClose(recentPrice.getPrice(pair));
        return binanceDataRepository.getIndicators(candlesticks);
    }

    private List<Tic> initData(Pairs pair, BinanceDataRepository dataRepository) {
        return dataRepository.getIndicators(dataRepository.updatePairCandles(pair));
    }
}
