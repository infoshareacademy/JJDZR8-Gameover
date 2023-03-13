package com.isa.config;

import com.isa.control.Data;
import com.isa.control.Wallet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CryptoWalletConfig {

    @Bean
    public Wallet wallet(){
        return new Wallet();
    }
}
