package com.isa.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;

public class ActiveTransactionDto{
        long idTransaction;
        CoinDto coin;
        @Positive
        double volume;
        double openPrice;
        double currentPrice;
        double stopLoss;
        double takeProfit;
        double profit;

        public ActiveTransactionDto() {}


        public ActiveTransactionDto(long idTransaction, CoinDto coin, double volume, double openPrice, double currentPrice, double stopLoss, double takeProfit, double profit) {
                this.idTransaction = idTransaction;
                this.coin = coin;
                this.volume = volume;
                this.openPrice = openPrice;
                this.currentPrice = currentPrice;
                this.stopLoss = stopLoss;
                this.takeProfit = takeProfit;
                this.profit = profit;
        }

        public long getIdTransaction() {
                return idTransaction;
        }

        public void setIdTransaction(long idTransaction) {
                this.idTransaction = idTransaction;
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

        public double getCurrentPrice() {
                return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
                this.currentPrice = currentPrice;
        }

        public double getStopLoss() {
                return stopLoss;
        }

        public void setStopLoss(double stopLoss) {
                this.stopLoss = stopLoss;
        }

        public double getTakeProfit() {
                return takeProfit;
        }

        public void setTakeProfit(double takeProfit) {
                this.takeProfit = takeProfit;
        }

        public double getProfit() {
                return profit;
        }

        public void setProfit(double profit) {
                this.profit = profit;
        }
}
