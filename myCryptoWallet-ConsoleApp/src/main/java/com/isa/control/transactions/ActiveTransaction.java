package com.isa.control.transactions;

import com.isa.control.Coin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActiveTransaction extends Transactions{

    private String openTransactionDate;
    private final double openPrice;
    private double currentPrice;
    private final Date newDate = new Date();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public ActiveTransaction(Coin coin, double volume) {
        super(coin, true, volume);
        this.openPrice = Double.parseDouble(coin.getLastPrice());
        this.currentPrice = Double.parseDouble(coin.getLastPrice());
        setOpenTransactionDate();
    }

    public String getOpenTransactionDate() {
        return openTransactionDate;
    }

    public void setOpenTransactionDate() {
        this.openTransactionDate = dateFormat.format(newDate);
    }


    public double getOpenPrice() {
        return openPrice;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }


}
