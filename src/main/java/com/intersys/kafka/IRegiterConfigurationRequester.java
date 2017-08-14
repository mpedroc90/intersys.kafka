package com.intersys.kafka;

import java.io.IOException;

/**
 * Created by Peter on 8/11/2017.
 */
public interface IRegiterConfigurationRequester {
    String GetJsonConfig(String to, KafkaClientType how) throws IOException;
}
