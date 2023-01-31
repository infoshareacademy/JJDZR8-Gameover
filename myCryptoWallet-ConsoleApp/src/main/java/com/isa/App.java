package com.isa;

import com.google.gson.Gson;
import com.isa.control.Data;
import com.isa.menu.Menu;

import java.util.*;

public class App {
    public static void main(String[] args) {
        new Endpoints();
        //Menu.getMenu();
        /*
        for (String symbol : Endpoints.getEndpoints()){
            System.out.println(symbol);
            String url = "https://api.binance.com/api/v3/ticker/24hr?symbols=[%22" + symbol + "BUSD%22]";
            String request = Data.sendHttpRequest(url);
            System.out.println(request);
        }
        */
        String url = Endpoints.buildRequest();
        String request = Data.sendHttpRequest(url);
        Coin[] coins = new Gson().fromJson(request, Coin[].class);

        for (Coin coin : coins) {
            System.out.println(coin.getSymbol() + " " + coin.getShortSymbol() + " " + coin.getName());
        }
    }

}

