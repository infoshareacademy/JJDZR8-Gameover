package com.isa;


import com.isa.control.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class CoinSearch {

    private Coin[] coins;

    public CoinSearch() {
        readCoinsFromJson();
    }

    public void readCoinsFromJson() {
        try {
            this.coins = Data.deserializeCoin();
        } catch (Exception e) {
            e.printStackTrace();
            this.coins = null;
        }
    }

    public List<Coin> search(String searchCriteria) {
        List<Coin> results = new ArrayList<>();
        for (Coin coin : coins) {
            if (coin.getSymbol().contains(searchCriteria)){
                results.add(coin);
            }
        }
        return results;
    }

    public void findYourToken() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj kryterium wyszukiwania (symbol):");
        String searchCriteria = sc.nextLine().toUpperCase();
        List<Coin> searchResults = search(searchCriteria);
        System.out.println("Wynik wyszukiwania:");
        for (Coin coin : searchResults) {
            System.out.println(coin.getSymbol() + " - " + coin.getOpenPrice() + "USD");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinSearch that = (CoinSearch) o;
        return Arrays.equals(coins, that.coins);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coins);
    }
}