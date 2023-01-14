package com.isa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Endpoints {
    private static List<String>endpoints = new ArrayList<>();

    public static List<String> getEndpoints() {
        return endpoints;
    }

    public static void setEndpoints() {
        Endpoints.endpoints = Data.deserializeEndpoints();
    }
    public static void addCoin(){
        Scanner sc = new Scanner(System.in);;
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
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("https://api.binance.com/api/v3/ticker/24hr?symbols=[");
        for (String endpoint : endpoints) {
            sBuilder.append("%22" + endpoint + "BUSD%22,");
        }
        sBuilder.replace(sBuilder.length() - 1, sBuilder.length(), "]");
        return sBuilder.toString();
    }
}
