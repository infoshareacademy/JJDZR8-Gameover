package com.isa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@NoArgsConstructor
public class CoinDto {

    private static Logger LOGGER = LoggerFactory.getLogger(CoinDto.class.getName());
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
        LOGGER.debug("DTO for Coin created.");
    }
}
