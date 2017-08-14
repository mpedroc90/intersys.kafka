package com.intersys.kafka;

import java.net.URL;

public interface IApiURIRequestCreator{
     URL Create(String regiterName, KafkaClientType clientType);
}
