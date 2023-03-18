package com.isa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoinDto {
    private String symbol;
    private String name;
    private double lastPrice;
    private double priceChangePercent;
    private double volume;

    public CoinDto(String symbol, String name, double lastPrice, double priceChangePercent, double volume) {
        this.symbol = symbol;
        this.name = name;
        this.lastPrice = lastPrice;
        this.priceChangePercent = priceChangePercent;
        this.volume = volume;
    }
}
