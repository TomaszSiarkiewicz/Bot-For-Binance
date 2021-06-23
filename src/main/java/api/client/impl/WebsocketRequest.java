package api.client.impl;

import api.client.SubscriptionErrorHandler;
import api.client.SubscriptionListener;
import api.client.impl.utils.Handler;


class WebsocketRequest<T> {

    final SubscriptionListener<T> updateCallback;
    final SubscriptionErrorHandler errorHandler;
    String signatureVersion = "2";
    String name;
    Handler<WebSocketConnection> connectionHandler;
    Handler<WebSocketConnection> authHandler = null;
    RestApiJsonParser<T> jsonParser;
    WebsocketRequest(SubscriptionListener<T> listener, SubscriptionErrorHandler errorHandler) {
        this.updateCallback = listener;
        this.errorHandler = errorHandler;
    }
}
