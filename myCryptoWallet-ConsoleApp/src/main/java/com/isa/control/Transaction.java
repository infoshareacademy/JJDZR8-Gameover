package com.isa.control;

public class Transaction {
    Integer idTransaction;
    Coin coin;
    Wallet wallet;

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
