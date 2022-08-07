package com.bilginyuksel.digitivation.pubsub;

public interface Broker {
    <T> void publish(String event, T message);

    <T> void subscribe(String event, Subscriber<T> subscriber);
}
