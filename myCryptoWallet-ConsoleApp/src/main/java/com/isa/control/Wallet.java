package com.isa.control;

import com.isa.control.Coin;

public class Wallet {
    Integer walletId;
    Integer coinValue;
    Integer walletSum;
    Coin coin;

    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public Integer getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(Integer coinValue) {
        this.coinValue = coinValue;
    }

    public Integer getWalletSum() {
        return walletSum;
    }

    public void setWalletSum(Integer walletSum) {
        this.walletSum = walletSum;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }
}
