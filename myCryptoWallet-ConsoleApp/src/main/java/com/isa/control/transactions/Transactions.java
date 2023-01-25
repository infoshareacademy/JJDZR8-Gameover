package com.isa.control.transactions;

import com.isa.control.Coin;

public class Transactions {
    private Integer idTransaction;
    private final Coin coin;
    private boolean isActive;
    private double volume;

    public Transactions(Coin coin, boolean isActive, double volume) {
        this.coin = coin;
        this.isActive = isActive;
        this.volume = volume;
    }
}
