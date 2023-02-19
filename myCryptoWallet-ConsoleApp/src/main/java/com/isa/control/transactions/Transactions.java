package com.isa.control.transactions;

import com.isa.control.Coin;
import com.isa.control.Endpoints;

import java.util.Objects;

public class Transactions implements Comparable<Transactions>{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return idTransaction == that.idTransaction && isActive == that.isActive && Double.compare(that.volume, volume) == 0 && Objects.equals(coin, that.coin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaction, coin, isActive, volume);
    }

    @Override
    public int compareTo(Transactions transactions) {
        if(idTransaction<transactions.getIdTransaction()) return 1;
        else if (idTransaction>transactions.getIdTransaction()) return -1;
        else return 0;

    }
}
