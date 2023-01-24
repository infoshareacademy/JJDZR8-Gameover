package com.isa.control;

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj symbol kryptowaluty");
        String userInput = sc.nextLine().toUpperCase();
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
       // Data.serializer(endpoints,"endpoints.json");
    }

    public static String buildRequest() {
        sBuilder.append("https://api.binance.com/api/v3/ticker/24hr?symbols=[");
        for (String endpoint : coinsNames.keySet()) {
            sBuilder.append("%22" + endpoint + "BUSD%22,");
        }
        sBuilder.replace(sBuilder.length() - 1, sBuilder.length(), "]");
        return sBuilder.toString();
    }
    public static String buildRequest(Map<String, String> map) {
        sBuilder.append("https://api.binance.com/api/v3/ticker/24hr?symbols=[");
        for (String value : map.keySet()) {
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
