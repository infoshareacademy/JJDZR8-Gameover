package com.isa.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClosedTransactionDto {

    private Logger LOGGER = LoggerFactory.getLogger(ClosedTransactionDto.class.getName());
    private CoinDto coin;
    private double volume;
    private double openPrice;
    private double closePrice;
    private double profit;

    public ClosedTransactionDto() {}

    public ClosedTransactionDto(CoinDto coin, double volume, double openPrice, double closePrice, double profit) {
        this.coin = coin;
        this.volume = volume;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.profit = profit;
        LOGGER.debug("DTO for closed transaction created.");
    }

    public CoinDto getCoin() {
        return coin;
    }

    public void setCoin(CoinDto coin) {
        this.coin = coin;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
