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

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
        return syncRequestClient.walletInfo(ZonedDateTime.now().toEpochSecond());
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

    public List<SymbolPrice> currentPrices() {
        return syncRequestClient.getSymbolPriceTicker();
    }


}

