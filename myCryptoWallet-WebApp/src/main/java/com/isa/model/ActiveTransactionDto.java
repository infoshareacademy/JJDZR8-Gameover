package com.isa.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveTransactionDto{

        private static Logger LOGGER = LoggerFactory.getLogger(ActiveTransactionDto.class.getName());
        private long idTransaction;
        private CoinDto coin;
        @Positive
        private double volume;
        private double openPrice;
        private double currentPrice;
        private double stopLoss;
        private double takeProfit;
        private double profit;
        private double transactionCost;

        public ActiveTransactionDto() {}

        public ActiveTransactionDto(long idTransaction, CoinDto coin, double volume, double openPrice, double currentPrice, double stopLoss, double takeProfit, double profit, double transactionCost) {
                this.idTransaction = idTransaction;
                this.coin = coin;
                this.volume = volume;
                this.openPrice = openPrice;
                this.currentPrice = currentPrice;
                this.stopLoss = stopLoss;
                this.takeProfit = takeProfit;
                this.profit = profit;
                this.transactionCost = transactionCost;
                LOGGER.debug("DTO for active transaction created.");
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

        public double getTransactionCost() {
                return transactionCost;
        }

        public void setTransactionCost(double transactionCost) {
                this.transactionCost = transactionCost;
        }
}
