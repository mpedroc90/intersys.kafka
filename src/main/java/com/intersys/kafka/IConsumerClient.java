package com.intersys.kafka;

import java.util.Properties;

/**
 * Created by Peter on 8/14/2017.
 */
public interface IConsumerClient extends IKafkaClient
{
        void  Run(java.util.function.Consumer<String> method);
        String getGroupId();

}
