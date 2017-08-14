package com.intersys.kafka;

import akka.kafka.ProducerSettings;
import akka.kafka.scaladsl.Producer;
import akka.stream.javadsl.Source;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.*;

import java.util.Properties;
import java.util.logging.Logger;


public class ProducerClient extends KafkaClient implements IProducerClient {

    private ProducerSettings<byte[], String> producerSettings;

    public ProducerClient(Properties properties)
    {
        super(properties);
        Logger.getGlobal().info("Creating Producer Client");
        topic = properties.getProperty(topic);
        producerSettings= ProducerSettings
                .create(system, new ByteArraySerializer(), new StringSerializer())
                .withBootstrapServers(host);

        Logger.getGlobal().info("Producer Client Created");
    }

    @Override
    public void SendMessage(String message)
    {
        Source.single(message)
            .map(elem -> new ProducerRecord<byte[], String>(getTopic(), elem))
            .runWith(Producer.plainSink(producerSettings), materializer);

    }




    static IProducerClient Create(Properties properties)
    {
        return new ProducerClient(properties);
    }


}
