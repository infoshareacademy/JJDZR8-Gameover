package com.isa.control;

import java.util.List;

public class Coins {
    private static List<Coin> coinList;

    public Coins() {
        setCoins();
    }

    public static List<Coin> getCoinList() {
        return coinList;
    }

    public static void setCoins() {
        Coins.coinList = Data.deserializeCoinList(Data.sendHttpRequest(Endpoints.buildRequest()));
        List<Coin> coinList = Coins.getCoinList();

        for (Coin coin : coinList) {
            coin.creatNameAndShortSymbolForCoin();
        }
        Coins.setCoinList(coinList);
    }

    public static void setCoinList(List<Coin> coinList) {
        Coins.coinList = coinList;
    }
}

