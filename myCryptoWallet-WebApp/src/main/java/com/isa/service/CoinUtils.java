package com.isa.service;

import com.isa.control.Coin;
import com.isa.control.Coins;
import com.isa.control.Endpoints;
import com.isa.model.CoinDto;

import java.util.ArrayList;
import java.util.List;

public class CoinUtils {
    private static final List<CoinDto> coinDtoList = new ArrayList<>();

    public static List<CoinDto> buildCoins() {
        List<Coin> coinList = Coins.getInstance().getCoinList();

        coinList.stream().forEach(coin -> {
            CoinDto coinDto = new CoinDto(coin.getShortSymbol(), coin.getName(), coin.getLastPrice(), coin.getVolume(), coin.getPriceChange());
            coinDtoList.add(coinDto);
        });
        return coinDtoList;
    }
}
