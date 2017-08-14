package com.intersys.kafka;


import com.intersys.kafka.fakes.FakeRegiterConfigurationProducerRequest;

import java.io.IOException;
import java.util.Properties;
import java.util.function.Consumer;

public class Main
{

    public static class ConsumerImpl implements Consumer<String>
    {
        @Override
        public void accept(String s) {
            System.out.println("Message: " + s );
        }

    }

    public static void main(String [] args) throws IOException
    {
        Producer();
        Consumer();
        Consumer();
    }

    private static void Consumer() throws IOException {
        Register register = new Register(new FakeRegiterConfigurationProducerRequest());

        Properties conf = register.Register("Indexer", KafkaClientType.Consumer);

        ConsumerClient
                .Create(conf)
                .Run(new ConsumerImpl());
    }



    private static void Producer() throws IOException {
        Register register = new Register(new FakeRegiterConfigurationProducerRequest());

        Properties conf = register.Register("hola", KafkaClientType.Producer);

         ProducerClient.Create(conf)
                 .SendMessage("Send Message");

    }
}
