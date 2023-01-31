package com.isa;

import com.isa.control.Data;

import java.util.*;

public class Endpoints {
    private static Map<String, String> coinsNames = new LinkedHashMap<>();
    private static List<String>endpoints = new ArrayList<>();
    private static StringBuilder sBuilder = new StringBuilder();

    public Endpoints() {
        setCoinsNames();
        setEndpoints();
    }

    public static void addCoin(){
        Scanner sc = new Scanner(System.in);;
        System.out.println("Podaj symbol kryptowaluty");
        String userInput = sc.nextLine();
        if (endpoints.contains(userInput)){
            System.out.println("Ta kryptowaluta znajduje się już na liście");
        }else{
            endpoints.add(userInput);
        }
        String response = Data.sendHttpRequest(Endpoints.buildRequest());
        if (response.contains("\"code\":-1100")){
            endpoints.remove(userInput);
            System.out.println("Kryptowaluta o takim symbolu nie istnieje");
        }
    }

    public static String buildRequest() {
        sBuilder.append("https://api.binance.com/api/v3/ticker/24hr?symbols=[");
        for (String endpoint : endpoints) {
            sBuilder.append("%22").append(endpoint).append("BUSD%22,");
        }
        sBuilder.replace(sBuilder.length() - 1, sBuilder.length(), "]");
        return sBuilder.toString();
    }
    public static String buildRequest(Map<String, String> map) {
        sBuilder.append("https://api.binance.com/api/v3/ticker/24hr?symbols=[");
        for (String value : map.keySet()) {
            System.out.println(value);
            sBuilder.append("%22" + value + "BUSD%22,");
        }
        sBuilder.replace(sBuilder.length() - 1, sBuilder.length(), "]");
        return sBuilder.toString();
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
