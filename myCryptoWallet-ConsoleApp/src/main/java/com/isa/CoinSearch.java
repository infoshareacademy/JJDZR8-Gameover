package com.isa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CoinSearch {

    public static List<Coin> readCoinsFromJson() {
        try {
            Gson gson = new Gson();
            Type coinListType = new TypeToken<List<Coin>>(){}.getType();
            return gson.fromJson(new FileReader("java/com/isa/data/coin.json"), coinListType);
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