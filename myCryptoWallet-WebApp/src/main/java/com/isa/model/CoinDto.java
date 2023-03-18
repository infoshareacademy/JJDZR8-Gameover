package com.isa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoinDto {
    private String symbol;
    private String name;
    private String lastPrice;
    private String priceChange;
    private String volume;

    public CoinDto(String symbol, String name, String lastPrice, String priceChange, String volume) {
        this.symbol = symbol;
        this.name = name;
        this.lastPrice = lastPrice;
        this.priceChange = priceChange;
        this.volume = volume;
    }
}
