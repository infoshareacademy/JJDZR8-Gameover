package com.isa.service;

import com.isa.control.Coin;
import com.isa.control.Coins;
import com.isa.control.Data;
import com.isa.control.Endpoints;
import com.isa.model.CoinDto;

import java.util.ArrayList;
import java.util.List;

public class CoinUtils {
    private static final List<CoinDto> coinDtoList = new ArrayList<>();

    public static List<CoinDto> buildCoins() {

        List<Coin> coinList = Coins.getInstance().getCoinList();

        coinList.forEach(coin -> {
            CoinDto coinDto = new CoinDto(coin.getShortSymbol(), coin.getName(), Double.parseDouble(coin.getLastPrice()),  Double.parseDouble(coin.getPriceChangePercent()), Double.parseDouble(coin.getVolume()));
            coinDtoList.add(coinDto);
        });
        return coinDtoList;
    }
}
