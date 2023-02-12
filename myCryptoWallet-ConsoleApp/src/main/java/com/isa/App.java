package com.isa;

import com.google.gson.Gson;
import com.isa.control.Data;
import com.isa.menu.Menu;

import java.util.*;

public class App {
    public static void main(String[] args) {
        new Endpoints();
        //Menu.getMenu();

        Gson gson = new Gson();
        String url = Endpoints.buildRequest();
        long startTime = System.currentTimeMillis();
        String request = Data.sendHttpRequest(url);
        long endTime = System.currentTimeMillis();
        Coin[] coins = gson.fromJson(request, Coin[].class);

        System.out.println(endTime-startTime);
        for (Coin coin : coins) {
            coin.setShortSymbol();
            coin.setName();
        }

        for (Coin coin : coins) {
            System.out.println(coin.getShortSymbol() + " " + coin.getName() + " Last Price: " + coin.getLastPrice());
        }
        long afterLoopsTime = System.currentTimeMillis();
        System.out.println(afterLoopsTime-endTime);
    }
}

