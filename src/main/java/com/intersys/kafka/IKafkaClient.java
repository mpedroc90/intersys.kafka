package com.intersys.kafka;

/**
 * Created by Peter on 8/14/2017.
 */
public interface IKafkaClient {
     String getTopic();
     String getHost() ;
     String getProperty(String property);
}
