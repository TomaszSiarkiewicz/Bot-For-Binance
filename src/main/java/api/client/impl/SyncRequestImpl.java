package api.client.impl;

import api.client.SyncRequestClient;
import api.client.model.enums.CandlestickInterval;
import api.client.model.market.Candlestick;
import api.client.model.market.CurrentAVGPrice;
import api.client.model.market.SymbolPrice;
import api.client.model.spot.wallet.*;
import api.client.model.trade.Order;

import java.math.BigDecimal;
import java.util.List;

public class SyncRequestImpl implements SyncRequestClient {

    private final RestApiRequestImpl requestImpl;

    SyncRequestImpl(RestApiRequestImpl requestImpl) {
        this.requestImpl = requestImpl;
    }


    @Override
    public CurrentAVGPrice getCurrentAVGprice(String symbol) {
        return RestApiInvoker.callSync(requestImpl.getCurrentAvgPrice(symbol));
    }

    @Override
    public SystemStatus getSystemStatus() {
        return null;
    }

    @Override
    public AccountStatus getAccountStatus(Long timestamp) {
        return null;
    }

    @Override
    public List<DepositHistory> getDepositHistory() {
        return null;
    }

    @Override
    public List<WidthrawHistory> getWidthrawHistory(Long timestamp) {
        return null;
    }


    @Override
    public List<Candlestick> getCandlestick(String symbol, CandlestickInterval interval, Long startTime,
                                            Long endTime, Integer limit) {
        return RestApiInvoker.callSync(requestImpl.getCandlestick(symbol, interval, startTime, endTime, limit));
    }

    @Override
    public TestConnectivity testConnectivity() {
        return RestApiInvoker.callSync(requestImpl.testConnectivity());
    }

    @Override
    public Order postOrderMarketBuy(String symbol, BigDecimal quoteOrderQty, Long timestamp) {
        return RestApiInvoker.callSync(requestImpl.postOrderMarketBuy(symbol, quoteOrderQty, timestamp));
    }

    @Override
    public List<CoinsInWalletInfo> walletInfo(Long timestamp) {
        return RestApiInvoker.callSync(requestImpl.walletInfo(timestamp));
    }

    @Override
    public Order postOrderMarketSell(String symbol, BigDecimal quantity, Long timestamp) {
        return RestApiInvoker.callSync(requestImpl.postOrderMarketSell(symbol, quantity, timestamp));
    }
    @Override
    public List<SymbolPrice> getSymbolPriceTicker(String symbol){
        return RestApiInvoker.callSync(requestImpl.getSymbolPriceTicker(symbol));
    }

}
