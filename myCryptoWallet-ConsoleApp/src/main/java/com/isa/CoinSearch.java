package com.isa;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class CoinSearch {

    public static Coin[] readCoinsFromJson() {
        try {
            Coin[] coins = Data.deserializeCoin();     // tutaj używamy już tylko metody deserializeCoin
            return coins;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Coin> search(Coin[] coins, String searchCriteria) {   //. tutaj zmieniłem, żeby metoda od razu zwracała listę, a nie tablicę
        List<Coin> results = new ArrayList<>();
        for (Coin coin : coins) {
            if (coin.getSymbol().contains(searchCriteria)){
                results.add(coin);
            }
        }
        return results;
    }

    public static void findYourToken() {
        Scanner sc = new Scanner(System.in);
        Coin[] coinList = readCoinsFromJson();
        System.out.println("Podaj kryterium wyszukiwania (symbol):");
        String searchCriteria = sc.nextLine();
        List<Coin> searchResults = CoinSearch.search(coinList, searchCriteria);     // tutaj minimalnie uprościłem kod
        System.out.println("Wynik wyszukiwania:");
        for (Coin coin : searchResults) {
            System.out.println(coin.getSymbol() + " - " + coin.getOpenPrice() + "USD");
        }
    }
}