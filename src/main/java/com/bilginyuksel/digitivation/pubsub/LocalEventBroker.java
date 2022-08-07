package com.bilginyuksel.digitivation.pubsub;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("ALL")
@Component
public class LocalEventBroker implements Broker{
    private final Map<String, List<Subscriber>> subscriberMap;

    public LocalEventBroker() {
        subscriberMap = new ConcurrentHashMap<>();
    }

    @Override
    public <T> void publish(String event, T message) {
        var subscribers = subscriberMap.getOrDefault(event, new ArrayList<>());
        subscribers.forEach(subscriber -> {
            if (Objects.equals(subscriber.getMessageClass(), message.getClass())) {
                throw new SubscriberUnExpectedMessageType();
            }

            subscriber.receive(message);
        });
    }

    @Override
    public <T> void subscribe(String event, Subscriber<T> subscriber) {
        subscriberMap.putIfAbsent(event, new ArrayList<>());
        subscriberMap.computeIfPresent(event, (s, subscribers) -> {
            subscribers.add(subscriber);
            return subscribers;
        });
    }
}
