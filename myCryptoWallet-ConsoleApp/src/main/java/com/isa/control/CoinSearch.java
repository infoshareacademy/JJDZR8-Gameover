package com.isa.control;


import java.util.*;


public class CoinSearch {

    private List<Coin> coins;

    public CoinSearch() {
        // readCoinsFromJson();
        this.coins = Coins.getInstance().getCoinList();

    }

    public List<Coin> search(String searchCriteria) {
        List<Coin> results = new ArrayList<>();
        for (Coin coin : coins) {
            if (coin.getSymbol().contains(searchCriteria)) {
                results.add(coin);
            }
        }
        return results;
    }

    public List<Coin> findYourToken() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj kryterium wyszukiwania (symbol):");
        String searchCriteria = sc.nextLine().toUpperCase();
        List<Coin> searchResults = search(searchCriteria);
        System.out.println("Wynik wyszukiwania:");
        for (Coin coin : searchResults) {
            System.out.println(coin.getSymbol() + " - " + coin.getOpenPrice() + "USD");
        }
        return searchResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinSearch that = (CoinSearch) o;
        return Objects.equals(coins, that.coins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coins);
    }
}
