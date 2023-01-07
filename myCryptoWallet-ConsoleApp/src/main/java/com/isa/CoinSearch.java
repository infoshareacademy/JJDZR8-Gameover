package com.isa;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.isa.Data.loadFile;


public class CoinSearch {

    public static Coin[] readCoinsFromJson() {
        try {
            Gson gson = new Gson();
            Coin[] coins = Data.deserializeCoin();
            return gson.fromJson(loadFile("Coin.json"), Coin[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Coin[] search(Coin[] coins, String searchCriteria) {
        List<Coin> results = new ArrayList<>();
        for (Coin coin : coins) {
            if (coin.getSymbol().contains(searchCriteria)){
                results.add(coin);
            }
        }
        return results.toArray(new Coin[coins.length]);
    }

    public static void findYourToken() {
        Scanner sc = new Scanner(System.in);
        Coin[] coinList = readCoinsFromJson();
        System.out.println("Podaj kryterium wyszukiwania (symbol):");
        String searchCriteria = sc.nextLine();
        Coin[] coinArray = CoinSearch.search(coinList, searchCriteria);
        List<Coin> searchResults = new ArrayList<>(Arrays.asList(coinArray));
        System.out.println("Wynik wyszukiwania:");
        for (Coin coin : searchResults) {
            System.out.println(coin.getSymbol() + " - " + coin.getOpenPrice() + "USD");
        }
    }
}