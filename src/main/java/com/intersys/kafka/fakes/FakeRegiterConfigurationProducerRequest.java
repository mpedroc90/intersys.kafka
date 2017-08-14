package com.intersys.kafka.fakes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intersys.kafka.*;
import com.intersys.kafka.IRegiterConfigurationRequester;

import java.io.IOException;
import java.util.*;

public class FakeRegiterConfigurationProducerRequest implements IRegiterConfigurationRequester {
    @Override
    public String GetJsonConfig(String to, KafkaClientType how) throws IOException {

            Properties properties= new Properties();
            properties.setProperty("host","localhost");
            properties.setProperty("port","9092");
            properties.setProperty("topic","topic");
            properties.setProperty("groupId","1");

            Map<String, Object> map = new TreeMap<>();

            for (Object key : properties.keySet()) {

                String value = properties.getProperty((String) key);

                map.put((String)key, value);
            }

            Gson gson = new GsonBuilder().create();
            return gson.toJson(map);


        }

        @SuppressWarnings("unchecked")
        private Map<String, Object> createTree(List<String> keys, Map<String, Object> map) {
            Map<String, Object> valueMap = (Map<String, Object>) map.get(keys.get(0));
            if (valueMap == null) {
                valueMap = new HashMap<String, Object>();
            }
            map.put(keys.get(0), valueMap);
            Map<String, Object> out = valueMap;
            if (keys.size() > 2) {
                out = createTree(keys.subList(1, keys.size()), valueMap);
            }
            return out;
        }

}
