package com.intersys.kafka;


import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.json.JSONObject;


public class Register {


    private IRegiterConfigurationRequester regiterConfigurationRequester;


    public Register(IRegiterConfigurationRequester regiterConfigurationRequester ) {
        this.regiterConfigurationRequester =regiterConfigurationRequester;
    }


    public Properties Register(String to, KafkaClientType how) throws IOException {


        String jsonResponse = regiterConfigurationRequester.GetJsonConfig(to, how);
        org.json.JSONObject jsonObject = new JSONObject(jsonResponse);
        Properties properties = new Properties();

        for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
            String key = it.next();
            properties.setProperty(key,jsonObject.getString(key));
        }

        return properties;
    }


}
