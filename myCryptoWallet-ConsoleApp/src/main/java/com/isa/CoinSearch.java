package com.isa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CoinSearch {

    public static List<Coin> readCoinsFromJson() {
        try {
            File file = new File("coin.json");
            if (file.exists()) {
                Gson gson = new Gson();
                Type coinListType = new TypeToken<List<Coin>>(){}.getType();
                return gson.fromJson(new FileReader("coin.json"), coinListType);
            } else {
                System.out.println("Plik coin.json nie został odnaleziony w katalogu z aplikacją.");
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Coin> search(List<Coin> coins, String searchCriteria) {
        List<Coin> results = new ArrayList<>();
        for (Coin coin : coins) {
            if (coin.getCoinName().contains(searchCriteria) || coin.getCoinSymbol().contains(searchCriteria)) {
                results.add(coin);
            }
        }
        return results;
    }

}