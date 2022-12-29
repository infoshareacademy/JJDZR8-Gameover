package com.isa;

import com.isa.menu.Menu;

public class App {
    public static void main(String[] args) {
        /*
        String s = Endpoints.buildRequest();

        String data = Data.sendHttpRequest(s);
        Data.saveToFile(data, "coin.json");

        Coin[] coins = Data.deserializeCoin();

        for (int i = 0; i < coins.length; i++) {
            System.out.print(coins[i].getSymbol().replace("BUSD", "") + " ");
            System.out.println(coins[i].getLastPrice());
        } */
        Menu.getMenu();

    }
}

