package com.isa.model;

public record ClosedTransactionDto (CoinDto coin, double volume, double openPrice, double closePrice, double profit){
}
