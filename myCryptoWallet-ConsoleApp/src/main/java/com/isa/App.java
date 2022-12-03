package com.isa;


import java.util.ArrayList;

public class App
{
    public static void main( String[] args ){
        // przykład działania serializacji i deserializacji

        ArrayList<Coin> coinList = new ArrayList<>();
        Coin coin1 = new Coin("BTC", "Bitcoin", 16548.38);
        Coin coin2 = new Coin("ETH", "Ethereum", 1213.9);
        Coin coin3 = new Coin("DOGE", "Dogecoin", 0.10475);

        coinList.add(coin1);
        coinList.add(coin2);
        coinList.add(coin3);

        Data.serializer(coinList, "coin.json");

        Coin[] coins = Data.deserializerCoin();
        for (Coin coin : coins){
            System.out.println(coin.getName());
            // output:
            // Bitcoin
            // Ethereum
            // DOGE
        }
    }
}

