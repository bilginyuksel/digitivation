package com.bilginyuksel.digitivation.pubsub;

public interface Subscriber<T> {
    void receive(T message);

    Class<T> getMessageClass();
}
