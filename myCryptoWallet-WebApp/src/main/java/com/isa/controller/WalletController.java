package com.isa.controller;

import com.isa.control.Data;
import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Set;

@Controller
public class WalletController {

    private final WalletService walletService;
    private final Wallet wallet;

    public WalletController(WalletService walletService, Wallet wallet){

        this.walletService = walletService;
        this.wallet = wallet;
    }

    @GetMapping("/show_wallets")
    public String selectWallet(Model model){
        Wallet emptyWallet = new Wallet();
        Set<String> allWalletsId = walletService.getAllWalletsId();
        // #TODO odświezyć ceny
        model.addAttribute("emptyWallet", emptyWallet);
        model.addAttribute("allWalletsId", allWalletsId);
        // #TODO jeśli mamy jeden wallet id w set to dodać if() i przekierować odrazu na "wallet"
        return "get_wallet";

    }
    @PostMapping("/new_wallet")
    public String createNewWallet(@ModelAttribute Wallet wallet, Model model){       // #TODO dodać walidację
        String id = wallet.getWalletId();
        if (walletService.isNewWalletIdExist(id)) return "redirect:/show_wallets";
        else{
            wallet.updateWallet();
            walletService.addWalletToWalletsMap(wallet);
            Wallet newWallet = walletService.findWalletById(id);
            model.addAttribute("walletById", newWallet);
            return "wallet";
        }

    }

    @PostMapping("/wallet/{id}")
    public String getWalletById(@PathVariable("id") String walletId, Model model){
        Wallet walletById = walletService.findWalletById(walletId);
        Set<ActiveTransaction> activeTransactions = walletById.getActiveTransactions();
        model.addAttribute("walletById", walletById);
        model.addAttribute("activeTransactions", activeTransactions);
        return "wallet";
    }
}
