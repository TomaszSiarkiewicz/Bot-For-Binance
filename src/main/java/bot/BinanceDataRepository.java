package bot;

import api.client.RequestOptions;
import api.client.SyncRequestClient;
import api.client.constant.PrivateConfig;
import api.client.model.enums.CandlestickInterval;
import api.client.model.market.Candlestick;
import api.client.model.market.SymbolPrice;
import api.client.model.spot.wallet.CoinsInWalletInfo;
import enums.Pairs;
import hibernate.entities.tics.Tic;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinanceDataRepository {
    private final SyncRequestClient syncRequestClient;

    public BinanceDataRepository() {
        RequestOptions options = new RequestOptions();
        syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, options);
    }

    public List<Candlestick> updatePairCandles(Pairs pair) {
        return syncRequestClient.getCandlestick(pair.getName(), CandlestickInterval.FIVE_MINUTES, null, null, 29);
    }


    public List<CoinsInWalletInfo> updateCoins() {
        List<CoinsInWalletInfo> coins = syncRequestClient.walletInfo(ZonedDateTime.now().toEpochSecond());
        Iterator<CoinsInWalletInfo> iterator = coins.iterator();
        while (iterator.hasNext()) {
            CoinsInWalletInfo coin = iterator.next();
            System.out.println(coin);
            if (coin.getFree().compareTo(BigDecimal.ZERO) < 1) {
                iterator.remove();
            }
        }
        return coins;
    }

    public List<Tic> getIndicators(List<Candlestick> candlesticks) {

        BarSeries series = new BaseBarSeriesBuilder().build();

        for (Candlestick candlestick : candlesticks) {
            series.addBar(ZonedDateTime.ofInstant(Instant.ofEpochMilli(candlestick.getCloseTime()), ZoneId.systemDefault()), candlestick.getOpen(), candlestick.getHigh(), candlestick.getLow(), candlestick.getClose(), candlestick.getVolume());
        }

        ClosePriceIndicator indicator = new ClosePriceIndicator(series);
        int maxIndex = indicator.getBarSeries().getEndIndex();

        RSIIndicator rsi = new RSIIndicator(indicator, 30);
        MACDIndicator macd = new MACDIndicator(rsi);
        EMAIndicator emaShort = new EMAIndicator(rsi, 9);
        List<Tic> tics = new ArrayList<>();
        for (int i = 0; i < maxIndex; i++) {
            Tic tic = new Tic();
            tic.setRsi(rsi.getValue(i).floatValue());
            tic.setMacd(macd.getValue(i).floatValue());
            tic.setEmashort(emaShort.getValue(i).floatValue());
            tic.setCloseTime(series.getBar(i).getEndTime());
            tics.add(tic);
        }
        return tics;
    }

    public SymbolPrice currentPrice(Pairs pair) {
        return syncRequestClient.getSymbolPriceTicker(pair.getName());
    }

    public List<Tic> updateIndicators(Pairs pair, List<Candlestick> candlesticks) {
        List<Candlestick> candlestickList = candlesticks;
        long lastCandleTime = candlestickList.get(candlestickList.size() - 2).getCloseTime();

        if (lastCandleTime < System.currentTimeMillis()) {
            System.out.println("new-----------------------------------------------------------------------------");
            candlestickList = updatePairCandles(pair);
            Candlestick candlestick = new Candlestick();
            candlestick.setOpenTime(candlestickList.get(candlestickList.size() - 1).getCloseTime() + 1);
            candlestick.setCloseTime(candlestick.getOpenTime() + 300000 - 1);
            candlestick.setClose(currentPrice(pair).getPrice());
            candlestick.setHigh(candlestick.getClose());
            candlestick.setLow(candlestick.getClose());
            candlestick.setOpen(candlestick.getClose());
            candlestick.setVolume(candlestickList.get(candlestickList.size() - 1).getVolume());
            candlestick.setIgnore(BigDecimal.ONE);
            candlestickList.add(candlestick);
        } else {
            System.out.println("update");
            candlestickList.get(candlestickList.size() - 1).setClose(currentPrice(pair).getPrice());

        }
        return getIndicators(candlestickList);
    }
}

