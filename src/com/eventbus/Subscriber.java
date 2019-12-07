package com.eventbus;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

interface Events {
    public boolean hasTopic(String topic);
    public void event(String msg);
}

public class Subscriber implements Events {
    public List<String> topics;
    public String uuid;
    Consumer<String> callback;

    Subscriber() {
        uuid = UUID.randomUUID().toString();
        topics = new ArrayList<>();
    }

    public boolean hasTopic(String topic) {
        try {
            return topics.contains(topic) ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    public void event(String msg) {
        this.callback.accept(uuid + " " + msg);
    }
}
