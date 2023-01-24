package com.isa.control;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private Integer idTransaction;
    private String openTransactionDate;
    private String closeTransactionDate;
    private final Coin coin;
    private final double openPrice;
    private double closePrice;
    private double lastPrice;
    private boolean isActive;
    private final Date newDate = new Date();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Transaction(Coin coin) {
        this.coin = coin;
        this.openPrice = Double.parseDouble(coin.getLastPrice());
        this.isActive = true;
        this.lastPrice = Double.parseDouble(coin.getLastPrice());
    }

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Coin getCoin() {
        return coin;
    }


    public String getOpenTransactionDate() {
        return openTransactionDate;
    }

    public void setOpenTransactionDate() {
        this.openTransactionDate = dateFormat.format(newDate);
    }

    public String getCloseTransactionDate() {
        return closeTransactionDate;
    }

    public void setCloseTransactionDate() {
        this.closeTransactionDate = dateFormat.format(newDate);
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
