package com.isa.mapper;

import com.isa.control.Coin;
import com.isa.entity.CoinEntity;

public class CoinEntityMapper {

    public static Coin mapCoinEntityToCoin(CoinEntity coinEntity){
        Coin coin = new Coin();
        coin.setSymbol(coinEntity.getSymbol());
        coin.creatNameAndShortSymbolForCoin();
        return coin;
    }

    public static CoinEntity mapCoinToEntity(Coin coin){
        CoinEntity coinEntity = new CoinEntity();
        coinEntity.setSymbol(coin.getSymbol());
        return coinEntity;
    }
}
