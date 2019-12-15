package com.eventbus;

public class Main{
    public static void main(String[] args) {

        System.out.println();

        EventBus eventBus = new EventBus();

        eventBus.subscribe(new String[]{"topic1"}, (data) -> {
            System.out.println(data);
        });

        eventBus.subscribe(new String[]{"topic1", "topic2"}, (data) -> {
            System.out.println(data);
        });

        System.out.println();

        eventBus.publishMT("topic1", "Message | topic1");
        eventBus.publishMT("topic2", "Message | topic2");

        eventBus.publishMT("topic1", "Message 2 | topic1");

    }

}
