package com.isa.service;

import com.isa.control.Data;
import com.isa.control.Wallet;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WalletService {

    private Map<String, Wallet> walletsMap;

    public WalletService(){
        this.walletsMap = Data.deserializeWallet();
    }


}
