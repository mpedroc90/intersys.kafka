package com.intersys.kafka;

import akka.Done;
import akka.kafka.ConsumerSettings;
import akka.kafka.Subscriptions;
import akka.kafka.javadsl.Consumer;
import akka.stream.javadsl.Sink;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;



public class ConsumerClient extends KafkaClient implements IConsumerClient {


    private ConsumerSettings<byte[], String> consumerSettings;

    private final String groupId;

    @Override
    public String getGroupId() {
        return groupId;
    }


    public ConsumerClient(Properties properties)
    {
        super(properties);
        groupId = properties.getProperty("groupId");

        consumerSettings=  ConsumerSettings.create(system, new ByteArrayDeserializer(), new StringDeserializer())
                .withBootstrapServers(host)
                .withGroupId(groupId)
                .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    }

    public static IConsumerClient Create(Properties conf)
    {
        return new ConsumerClient(conf);
    }

    public  void  Run(java.util.function.Consumer<String> method)
    {
        final Rocket rocket = new Rocket();
        Consumer.atMostOnceSource(consumerSettings, Subscriptions.topics(topic))
                .mapAsync(1, record -> rocket.launch((String) record.value(),method))
                .runWith(Sink.ignore(), materializer);
    }


    private  class Rocket {
        CompletionStage<Done> launch(String value, java.util.function.Consumer<String> method)
        {
            method.accept(value);
            return CompletableFuture.completedFuture(Done.getInstance());
        }
    }
}
