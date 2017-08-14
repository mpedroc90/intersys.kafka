package com.intersys.kafka;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;

import java.util.Properties;




public abstract class KafkaClient implements IKafkaClient {


    protected ActorSystem system = ActorSystem.create("KafkaClient");
    protected ActorMaterializer materializer =  ActorMaterializer.create(system);

    protected String topic ;
    protected String host;
    protected Properties properties;

    public String getTopic(){
        return topic;
    }

    public String getHost() {
        return host;
    }


    @Override
    public String getProperty(String property) {
        return properties.getProperty(property);
    }

    protected KafkaClient(Properties properties)
    {

        topic = properties.getProperty("topic");
        host = properties.getProperty("host")+":"+properties.getProperty("port");
        this.properties = properties;
    }
}
