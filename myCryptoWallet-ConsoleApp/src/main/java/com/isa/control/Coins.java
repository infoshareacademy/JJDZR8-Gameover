package com.isa.control;

import java.util.List;

public class Coins {
    private static Coins INSTANCE;

    private List<Coin> coinList;

    private Coins() {
        setCoins();

    }

    public static Coins getInstance() {
        if (INSTANCE == null) {
            new Endpoints();
            INSTANCE = new Coins();
        }
        return INSTANCE;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    private void setCoins() {
        this.coinList = Data.deserializeCoinList(Data.sendHttpRequest(Endpoints.buildRequest()));
        List<Coin> coinList = this.getCoinList();

        for (Coin coin : coinList) {
            coin.creatNameAndShortSymbolForCoin();
        }
        this.setCoinList(coinList);
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }
}

