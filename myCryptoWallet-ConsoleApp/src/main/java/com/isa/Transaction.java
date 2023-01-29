package com.isa;

import java.util.HashMap;
import java.util.Map;

public class Transaction {
    Integer idTransaction;
    Map<Coin, Integer> items = new HashMap<>();

    public Transaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }



     public void addQuantitiOfCoins(Coin coin) {
        Integer currentQuantity = items.getOrDefault(coin, 0);
        items.put(coin, ++currentQuantity);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + idTransaction +
                ", items=" + items +
                '}';
    }
}
