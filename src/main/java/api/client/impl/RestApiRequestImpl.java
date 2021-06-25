package api.client.impl;

import api.client.RequestOptions;
import api.client.exception.BinanceApiException;
import api.client.impl.utils.JsonWrapperArray;
import api.client.impl.utils.UrlParamsBuilder;
import api.client.model.enums.CandlestickInterval;
import api.client.model.enums.OrderRespType;
import api.client.model.enums.OrderSide;
import api.client.model.enums.OrderType;
import api.client.model.market.Candlestick;
import api.client.model.market.CurrentAVGPrice;
import api.client.model.market.SymbolPrice;
import api.client.model.spot.wallet.*;
import api.client.model.trade.Order;
import com.alibaba.fastjson.JSONArray;
import okhttp3.Request;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;


class RestApiRequestImpl {

    private final String apiKey;
    private final String secretKey;
    private final String serverUrl;

    RestApiRequestImpl(String apiKey, String secretKey, RequestOptions options) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.serverUrl = options.getUrl();
    }

    private Request createRequestByGet(String address, UrlParamsBuilder builder) {
        System.out.println(serverUrl);
        return createRequestByGet(serverUrl, address, builder);
    }

    private Request createRequestByGet(String url, String address, UrlParamsBuilder builder) {
        return createRequest(url, address, builder);
    }

    private Request createRequest(String url, String address, UrlParamsBuilder builder) {
        String requestUrl = url + address;
        System.out.print(requestUrl);
        if (builder != null) {
            if (builder.hasPostParam()) {
                return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                        .addHeader("Content-Type", "application/json")
                        .build();
            } else {
                return new Request.Builder().url(requestUrl + builder.buildUrl())
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .build();
            }
        } else {
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
        }
    }

    private Request createRequestWithSignature(String url, String address, UrlParamsBuilder builder) {
        if (builder == null) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Invoking] Builder is null when create request with Signature");
        }
        String requestUrl = url + address;
        new ApiSignature().createSignature(apiKey, secretKey, builder);
        if (builder.hasPostParam()) {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else if (builder.checkMethod("PUT")) {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .put(builder.buildPostBody())
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else if (builder.checkMethod("DELETE")) {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .delete()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        }
    }

    private Request createRequestByPostWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder.setMethod("POST"));
    }

    private Request createRequestByGetWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder);
    }

    private Request createRequestByPutWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder.setMethod("PUT"));
    }

    private Request createRequestByDeleteWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder.setMethod("DELETE"));
    }

    private Request createRequestWithApikey(String url, String address, UrlParamsBuilder builder) {
        if (builder == null) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Invoking] Builder is null when create request with Signature");
        }
        String requestUrl = url + address;
        requestUrl += builder.buildUrl();
        if (builder.hasPostParam()) {
            return new Request.Builder().url(requestUrl)
                    .post(builder.buildPostBody())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else if (builder.checkMethod("DELETE")) {
            return new Request.Builder().url(requestUrl)
                    .delete()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else if (builder.checkMethod("PUT")) {
            return new Request.Builder().url(requestUrl)
                    .put(builder.buildPostBody())
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else {
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        }
    }


    RestApiRequest<List<Candlestick>> getCandlestick(String symbol, CandlestickInterval interval, Long startTime,
                                                     Long endTime, Integer limit) {
        RestApiRequest<List<Candlestick>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("interval", interval)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/api/v1/klines", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Candlestick> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEachAsArray((item) -> {
                Candlestick element = new Candlestick();
                element.setOpenTime(item.getLongAt(0));
                element.setOpen(item.getBigDecimalAt(1));
                element.setHigh(item.getBigDecimalAt(2));
                element.setLow(item.getBigDecimalAt(3));
                element.setClose(item.getBigDecimalAt(4));
                element.setVolume(item.getBigDecimalAt(5));
                element.setCloseTime(item.getLongAt(6));
                element.setQuoteAssetVolume(item.getBigDecimalAt(7));
                element.setNumTrades(item.getIntegerAt(8));
                element.setTakerBuyBaseAssetVolume(item.getBigDecimalAt(9));
                element.setTakerBuyQuoteAssetVolume(item.getBigDecimalAt(10));
                element.setIgnore(item.getBigDecimalAt(11));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    RestApiRequest<SystemStatus> getSystemStatus() {
        RestApiRequest<SystemStatus> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGet("/sapi/v1/system/status", builder);

        request.jsonParser = (jsonWrapper -> {
            SystemStatus result = new SystemStatus();
            result.setStatus(jsonWrapper.getString("status"));
            result.setMsg(jsonWrapper.getString("msg"));
            return result;
        });
        return request;
    }

    RestApiRequest<List<DepositHistory>> getDepositHistory() {
        RestApiRequest<List<DepositHistory>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/sapi/v1/capital/deposit/hisrec", builder);

        request.jsonParser = (jsonWrapper -> {
            List<DepositHistory> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach(item -> {
                DepositHistory element = new DepositHistory();
                element.setAmount(item.getBigDecimal("amount"));
                element.setCoin(item.getString("coin"));
                element.setNetwork(item.getString("network"));
                element.setStatus(item.getString("status"));
                element.setAddress(item.getString("address"));
                element.setTxId(item.getString("txId"));
                element.setInsertTime(item.getLong("insertTime"));
                element.setTransferType(item.getString("transferType"));
                element.setConfirmTimes(item.getString("confirmTimes"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    RestApiRequest<List<WidthrawHistory>> getWithdrawHistory(Long timestamp) {
        RestApiRequest<List<WidthrawHistory>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build().putToUrl("timestamp", timestamp);
        request.request = createRequestByGetWithSignature("/sapi/v1/capital/withdraw/history", builder);

        request.jsonParser = (jsonWrapper -> {
            List<WidthrawHistory> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach(item -> {
                WidthrawHistory element = new WidthrawHistory();
                element.setAddress(item.getString("address"));
                element.setAmount(item.getBigDecimal("amount"));
                element.setApplyTime(item.getString("applyTime"));
                element.setCoin(item.getString("coin"));
                element.setId(item.getString("id"));
                element.setNetwork(item.getString("network"));
                element.setTransferType(item.getString("transferType"));
                element.setStatus(item.getString("status"));
                element.setTxId(item.getString("txId"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    RestApiRequest<AccountStatus> getAccountStatus(Long timestamp) {
        RestApiRequest<AccountStatus> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build().putToUrl("timestamp", timestamp);
        request.request = createRequestByGetWithSignature("/sapi/v1/account/status", builder);

        request.jsonParser = (jsonWrapper -> {
            AccountStatus result = new AccountStatus();
            result.setData(jsonWrapper.getString("data"));
            return result;
        });
        return request;
    }

    RestApiRequest<CurrentAVGPrice> getCurrentAvgPrice(String symbol) {
        RestApiRequest<CurrentAVGPrice> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build().putToUrl("symbol", symbol);
        request.request = createRequestByGet("/api/v3/avgPrice", builder);

        request.jsonParser = (jsonWrapper -> {
            CurrentAVGPrice result = new CurrentAVGPrice();
            result.setMins(jsonWrapper.getInteger("mins"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            return result;
        });
        return request;
    }

    RestApiRequest<TestConnectivity> testConnectivity() {
        RestApiRequest<TestConnectivity> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGet("/api/v3/ping", builder);
        request.jsonParser = (json -> {
            TestConnectivity testConnectivity = new TestConnectivity();
            return testConnectivity;
        });
        return request;
    }

    RestApiRequest<Order> postOrderMarketBuy(String symbol, BigDecimal quoteOrderQty, Long timestamp) {
        RestApiRequest<Order> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("side", OrderSide.BUY)
                .putToUrl("type", OrderType.MARKET)
                .putToUrl("quoteOrderQty", quoteOrderQty)
                .putToUrl("newOrderRespType", OrderRespType.RESULT)
                .putToUrl("timestamp", timestamp);

        request.request = createRequestByPostWithSignature("/api/v3/order", builder);

        request.jsonParser = (jsonWrapper -> {
            Order result = new Order();
            result.setClientOrderId(jsonWrapper.getString("clientOrderId"));
            result.setExecutedQty(jsonWrapper.getBigDecimal("executedQty"));
            result.setOrderId(jsonWrapper.getString("orderId"));
            result.setOrigQty(jsonWrapper.getBigDecimal("origQty"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            result.setSide(jsonWrapper.getString("side"));
            result.setStatus(jsonWrapper.getString("status"));
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setTimeInForce(jsonWrapper.getString("timeInForce"));
            result.setType(jsonWrapper.getString("type"));
            result.setOrderListId(jsonWrapper.getString("orderListId"));
            result.setTransactTime(jsonWrapper.getLong("transactTime"));
            result.setCummulativeQuoteQty(jsonWrapper.getBigDecimal("cummulativeQuoteQty"));
            return result;
        });
        return request;
    }

    RestApiRequest<Order> postOrderMarketSell(String symbol, BigDecimal quantity, Long timestamp) {
        RestApiRequest<Order> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("side", OrderSide.SELL)
                .putToUrl("type", OrderType.MARKET)
                .putToUrl("quoteOrderQty", quantity)
                .putToUrl("newOrderRespType", OrderRespType.RESULT)
                .putToUrl("timestamp", timestamp);

        request.request = createRequestByPostWithSignature("/api/v3/order", builder);

        request.jsonParser = (jsonWrapper -> {
            Order result = new Order();
            result.setClientOrderId(jsonWrapper.getString("clientOrderId"));
            result.setExecutedQty(jsonWrapper.getBigDecimal("executedQty"));
            result.setOrderId(jsonWrapper.getString("orderId"));
            result.setOrigQty(jsonWrapper.getBigDecimal("origQty"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            result.setSide(jsonWrapper.getString("side"));
            result.setStatus(jsonWrapper.getString("status"));
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setTimeInForce(jsonWrapper.getString("timeInForce"));
            result.setType(jsonWrapper.getString("type"));
            result.setOrderListId(jsonWrapper.getString("orderListId"));
            result.setTransactTime(jsonWrapper.getLong("transactTime"));
            result.setCummulativeQuoteQty(jsonWrapper.getBigDecimal("cummulativeQuoteQty"));
            return result;
        });
        return request;
    }

    RestApiRequest<List<CoinsInWalletInfo>> walletInfo(Long timestamp) {
        RestApiRequest<List<CoinsInWalletInfo>> request = new RestApiRequest();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("timestamp", timestamp);

        request.request = createRequestByGetWithSignature("/sapi/v1/capital/config/getall", builder);

        request.jsonParser = (jsonWrapper -> {
            List<CoinsInWalletInfo> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                CoinsInWalletInfo coin = new CoinsInWalletInfo();
                coin.setCoin(item.getString("coin"));
                coin.setFree(item.getBigDecimal("free"));
                coin.setLocked(item.getBigDecimal("locked"));
                coin.setName(item.getString("name"));
                result.add(coin);
            });
            return result;
        });
        return request;
    }

    RestApiRequest<List<WidthrawHistory>> getWidthrawHistory(Long timestamp) {
        RestApiRequest<List<WidthrawHistory>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build().putToUrl("timestamp", timestamp);
        request.request = createRequestByGetWithSignature("/sapi/v1/capital/withdraw/history", builder);

        request.jsonParser = (jsonWrapper -> {
            List<WidthrawHistory> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach(item -> {
                WidthrawHistory element = new WidthrawHistory();
                element.setAddress(item.getString("address"));
                element.setAmount(item.getBigDecimal("amount"));
                element.setApplyTime(item.getString("applyTime"));
                element.setCoin(item.getString("coin"));
                element.setId(item.getString("id"));
                element.setNetwork(item.getString("network"));
                element.setTransferType(item.getString("transferType"));
                element.setStatus(item.getString("status"));
                element.setTxId(item.getString("txId"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    RestApiRequest<List<SymbolPrice>> getSymbolPriceTicker() {
        RestApiRequest<List<SymbolPrice>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGet("/api/v3/ticker/price", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SymbolPrice> result = new LinkedList<>();
            JsonWrapperArray dataArray = new JsonWrapperArray(new JSONArray());
            if (jsonWrapper.containKey("data")) {
                dataArray = jsonWrapper.getJsonArray("data");
            } else {
                dataArray.add(jsonWrapper.convert2JsonObject());
            }
            dataArray.forEach((item) -> {
                SymbolPrice element = new SymbolPrice();
                element.setSymbol(item.getString("symbol"));
                element.setPrice(item.getBigDecimal("price"));
                result.add(element);
            });

            return result;
        });
        return request;
    }


}
