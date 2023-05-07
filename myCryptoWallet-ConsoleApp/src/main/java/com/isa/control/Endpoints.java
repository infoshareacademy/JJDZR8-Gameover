package com.isa.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Endpoints {

    private static Logger LOGGER = LoggerFactory.getLogger(Endpoints.class.getName());
    private static Map<String, String> coinsNames = new HashMap<>();
    private static List<String>endpoints = new ArrayList<>();
    private static StringBuilder sBuilder = new StringBuilder();

    public Endpoints() {
        setCoinsNames();
        setEndpoints();
    }

    public static void addCoin(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj symbol kryptowaluty");
        String userInput = sc.nextLine().toUpperCase();
        if (endpoints.contains(userInput)){
            System.out.println("Ta kryptowaluta znajduje się już na liście");
        }else{
            endpoints.add(userInput);
            LOGGER.trace("Added {} to the endpoints list.", userInput);
        }
        String response = Data.sendHttpRequest(Endpoints.buildRequest());
        if (response.contains("\"code\":-1100")){
            endpoints.remove(userInput);
            LOGGER.trace("The coin {} does not exist.", userInput);
            System.out.println("Kryptowaluta o takim symbolu nie istnieje");
        }
       // Data.serializer(endpoints,"endpoints.json");
    }

    public static String buildRequest() {
        sBuilder.append("https://api.binance.com/api/v3/ticker/24hr?symbols=[");
        for (String endpoint : endpoints) {
            sBuilder.append("%22").append(endpoint).append("BUSD%22,");
        }
        sBuilder.replace(sBuilder.length() - 1, sBuilder.length(), "]");
        LOGGER.trace("Request: {} created.", sBuilder.toString());
        return sBuilder.toString();
    }
    public static String buildRequest(Map<String, String> map) {
        sBuilder.append("https://api.binance.com/api/v3/ticker/24hr?symbols=[");
        for (String value : map.keySet()) {
            System.out.println(value);
            sBuilder.append("%22" + value + "BUSD%22,");
        }
        sBuilder.replace(sBuilder.length() - 1, sBuilder.length(), "]");
        LOGGER.trace("Request: {} created.", sBuilder.toString());
        return sBuilder.toString();
    }
    public static String buildRequest(String string){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.binance.com/api/v3/ticker/24hr?symbols=[");
        stringBuilder.append("%22" + string + "BUSD%22,");
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "]");
        LOGGER.trace("Request: {} created.", stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static Map<String, String> getCoinsNames() {
        return coinsNames;
    }

    public static List<String> getEndpoints() {
        return endpoints;
    }

    public static void setCoinsNames() {
        Endpoints.coinsNames = Data.deserializeCoinsNames();
    }

    public static void setEndpoints() {
        endpoints.addAll(coinsNames.keySet());
    }
}
