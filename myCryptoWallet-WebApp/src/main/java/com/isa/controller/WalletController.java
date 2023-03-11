package com.isa.controller;

import com.isa.config.CryptoWalletConfig;
import com.isa.control.Wallet;
import org.springframework.stereotype.Controller;

@Controller
public class WalletController {

    private final Wallet wallet;

    public WalletController(){
        CryptoWalletConfig cryptoWalletConfig = new CryptoWalletConfig();
        this.wallet = cryptoWalletConfig.wallet();
    }
}
