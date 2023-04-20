package com.isa.model;

import jakarta.validation.constraints.*;

import java.util.Set;

public class WalletDto{
        @Size(min = 3)
        private String walletId;
        private double walletSum;
        private double profitLoss;
        private double historicalProfitLoss;
        private double transactionCosts;
        @PositiveOrZero
        private double walletBalance;
        private Set<ClosedTransactionDto> transactionHistoryDtos;
        private Set<ActiveTransactionDto> activeTransactionDtos;

        public WalletDto() {}

        public WalletDto(String walletId, double walletSum, double profitLoss, double historicalProfitLoss, double transactionCosts, double walletBalance, Set<ClosedTransactionDto> transactionHistoryDtos, Set<ActiveTransactionDto> activeTransactionDtos) {
                this.walletId = walletId;
                this.walletSum = walletSum;
                this.profitLoss = profitLoss;
                this.historicalProfitLoss = historicalProfitLoss;
                this.transactionCosts = transactionCosts;
                this.walletBalance = walletBalance;
                this.transactionHistoryDtos = transactionHistoryDtos;
                this.activeTransactionDtos = activeTransactionDtos;
        }

        public String getWalletId() {
                return walletId;
        }

        public void setWalletId(String walletId) {
                this.walletId = walletId;
        }

        public double getWalletSum() {
                return walletSum;
        }

        public void setWalletSum(double walletSum) {
                this.walletSum = walletSum;
        }

        public double getProfitLoss() {
                return profitLoss;
        }

        public void setProfitLoss(double profitLoss) {
                this.profitLoss = profitLoss;
        }

        public double getHistoricalProfitLoss() {
                return historicalProfitLoss;
        }

        public void setHistoricalProfitLoss(double historicalProfitLoss) {
                this.historicalProfitLoss = historicalProfitLoss;
        }

        public double getTransactionCosts() {
                return transactionCosts;
        }

        public void setTransactionCosts(double transactionCosts) {
                this.transactionCosts = transactionCosts;
        }

        public double getWalletBalance() {
                return walletBalance;
        }

        public void setWalletBalance(double walletBalance) {
                this.walletBalance = walletBalance;
        }

        public Set<ClosedTransactionDto> getTransactionHistoryDtos() {
                return transactionHistoryDtos;
        }

        public void setTransactionHistoryDtos(Set<ClosedTransactionDto> transactionHistoryDtos) {
                this.transactionHistoryDtos = transactionHistoryDtos;
        }

        public Set<ActiveTransactionDto> getActiveTransactionDtos() {
                return activeTransactionDtos;
        }

        public void setActiveTransactionDtos(Set<ActiveTransactionDto> activeTransactionDtos) {
                this.activeTransactionDtos = activeTransactionDtos;
        }
}
