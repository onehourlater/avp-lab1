package com.eventbus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class EventBus extends Thread {
    public List<Subscriber> subscribers = new ArrayList<Subscriber>();

    public void run(){
        System.out.println("Current thread name: "
                + Thread.currentThread().getName());

        System.out.println("run() method called");
    }

    EventBus(){

    }

    public void subscribe(String[] topics, Consumer<String> callback){

        Subscriber subscriber = new Subscriber();

        for (String topic : topics) {
            if (!subscriber.hasTopic(topic)) {
                subscriber.topics.add(topic);
                System.out.println(subscriber.uuid + " have been successfully subscribed to " + topic);
            }
        }

        subscriber.callback = callback;

        subscribers.add(subscriber);

    }

    public void publishMT(String topic, String msg){
        new Thread(new Runnable() {
            @Override

            public void run() {
                subscribers.forEach((subscriber)->{
                    if(subscriber.hasTopic(topic)) {
                        // минимизировав блокировки и thread-safe
                        synchronized(subscriber) {
                            subscriber.event("(" + Thread.currentThread().getName() + ") " + msg);
                        }
                        // произвольно
                        // subscriber.event("(" + Thread.currentThread().getName() + ") " + msg);
                    }
                });
            }
        }).start();

    }
}
