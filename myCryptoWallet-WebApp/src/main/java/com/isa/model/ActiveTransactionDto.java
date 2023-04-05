package com.isa.model;

public record ActiveTransactionDto(long idTransaction, CoinDto coin, double volume, double openPrice, double currentPrice, double stopLoss, double takeProfit, double profit) {
}
