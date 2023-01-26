package com.isa.control.transactions;

import com.isa.control.Coin;
import com.isa.control.Endpoints;

public class Transactions {
    private final long idTransaction;
    private final Coin coin;
    private final boolean isActive;
    private final double volume;

    public Transactions(Coin coin, boolean isActive, double volume, long idTransaction) {
        this.coin = coin;
        this.isActive = isActive;
        this.volume = volume;
        this.idTransaction = idTransaction;
    }

    public long getIdTransaction() {
        return idTransaction;
    }

    public Coin getCoin() {
        return coin;
    }

    public boolean isActive() {
        return isActive;
    }

    public double getVolume() {
        return volume;
    }
    public boolean checkEndpointsName(){
        return Endpoints.getCoinsNames().containsKey(coin.getShortSymbol());
    }
}
