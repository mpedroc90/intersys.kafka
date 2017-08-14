package com.intersys.kafka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegiterConfigurationRequesterImpl implements IRegiterConfigurationRequester {


    private IApiURIRequestCreator apiURIRequestCreator;

    public RegiterConfigurationRequesterImpl(IApiURIRequestCreator apiURIRequestCreator) {
        this.apiURIRequestCreator = apiURIRequestCreator;
    }

    @Override
    public String GetJsonConfig(String regiterName, KafkaClientType clientType) throws IOException {

        try {

            HttpURLConnection conn;
            URL url = apiURIRequestCreator.Create(regiterName,clientType);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200)
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            StringBuilder output = new StringBuilder();
            System.out.println("Output from Server .... \n");
            String line;
            while ((line = br.readLine()) != null)
                output.append(line);

            conn.disconnect();
            return line;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}