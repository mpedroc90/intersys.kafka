package com.intersys.kafka;

import java.util.Properties;

/**
 * Created by Peter on 8/14/2017.
 */
public interface IProducerClient extends IKafkaClient
 {
     void SendMessage(String message);
}
