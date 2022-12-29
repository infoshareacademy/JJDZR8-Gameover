package com.isa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
    public static void findYourToken() {
        Scanner sc = new Scanner(System.in);
        List<Coin> coinList = readCoinsFromJson();
        List<Coin> coins = new ArrayList<>();
        for (Coin coin : coinList) {
            coins.add(new Coin(coin.getCoinSymbol(), coin.getCoinName(), coin.getPrice()));
        }
        System.out.println("Podaj kryterium wyszukiwania (nazwa lub symbol):");
        String searchCriteria = sc.nextLine();
        List<Coin> searchResults = CoinSearch.search(coins, searchCriteria);
        System.out.println("Wynik wyszukiwania:");
        for (Coin coin : searchResults) {
            System.out.println(coin.getCoinSymbol() + " - " + coin.getCoinName() + " - " + coin.getPrice() + " USD");
        }
    }
}