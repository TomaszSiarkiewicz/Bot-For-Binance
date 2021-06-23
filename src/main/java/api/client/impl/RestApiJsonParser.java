package api.client.impl;


import api.client.impl.utils.JsonWrapper;

@FunctionalInterface
public interface RestApiJsonParser<T> {

    T parseJson(JsonWrapper json);
}
