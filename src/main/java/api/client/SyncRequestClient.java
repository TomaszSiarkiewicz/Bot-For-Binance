package api.client;

import api.client.impl.BinanceApiInternalFactory;
import api.client.model.enums.CandlestickInterval;
import api.client.model.market.Candlestick;
import api.client.model.market.CurrentAVGPrice;
import api.client.model.market.SymbolPrice;
import api.client.model.spot.wallet.*;
import api.client.model.trade.Order;

import java.math.BigDecimal;
import java.util.List;

/**
 * Synchronous request interface, invoking Binance RestAPI via synchronous
 * method.<br>
 * All methods in this interface will be blocked until the RestAPI response.
 * <p>
 * If the invoking failed or timeout, the
 * {@link api.client.exception.BinanceApiException} will be thrown.
 */
public interface SyncRequestClient {

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create() {
        return create("", "", new RequestOptions());
    }

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @param apiKey    The public key applied from binance.
     * @param secretKey The private key applied from binance.
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create(String apiKey, String secretKey) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, new RequestOptions());
    }

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @param apiKey    The public key applied from binance.
     * @param secretKey The private key applied from binance.
     * @param options   The request option.
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create(String apiKey, String secretKey, RequestOptions options) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, options);
    }


    CurrentAVGPrice getCurrentAVGprice(String symbol);

    SystemStatus getSystemStatus();

    AccountStatus getAccountStatus(Long timestamp);

    List<DepositHistory> getDepositHistory();       // last 90 days

    List<WidthrawHistory> getWidthrawHistory(Long timestamp);       // last 90 days

    List<Candlestick> getCandlestick(String symbol, CandlestickInterval interval, Long startTime, Long endTime, Integer limit);

    TestConnectivity testConnectivity();

    Order postOrderMarketBuy(String symbol, BigDecimal quoteOrderQty, Long timestamp);

    Order postOrderMarketSell(String symbol, BigDecimal quantity, Long timestamp);

    List<CoinsInWalletInfo> walletInfo(Long timestamp);

    SymbolPrice getSymbolPriceTicker(String pair);

}