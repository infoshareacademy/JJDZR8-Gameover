package com.isa.control.transactions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.isa.control.Coin;
import com.isa.control.Endpoints;

import java.util.Objects;

public class Transactions implements Comparable<Transactions>{
    private long idTransaction;
    private Coin coin;
    private boolean isActive;
    private double volume;

    public Transactions(){}
    public Transactions( Coin coin, boolean isActive, double volume, long idTransaction) {
        this.coin = coin;
        this.isActive = isActive;
        this.volume = volume;
        this.idTransaction = idTransaction;
    }
    @JsonGetter("idTransaction")
    public long getIdTransaction() {
        return idTransaction;
    }

    @JsonGetter("coin")
    public Coin getCoin() {
        return coin;
    }

    @JsonGetter("isActive")
    public boolean isActive() {
        return isActive;
    }

    @JsonGetter("volume")
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

    @JsonSetter("idTransaction")
    public void setIdTransaction(long idTransaction) {
        this.idTransaction = idTransaction;
    }

    @JsonSetter("coin")
    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    @JsonSetter("active")
    public void setActive(boolean active) {
        isActive = active;
    }
    @JsonSetter("volume")
    public void setVolume(double volume) {
        this.volume = volume;
    }
}
