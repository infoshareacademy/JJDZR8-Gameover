package com.isa.controller;

import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.service.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class WalletController {

    private final WalletService walletService;
    private final Wallet wallet;
    private Set<String> allWalletsId;

    public WalletController(WalletService walletService, Wallet wallet){

        this.walletService = walletService;
        this.wallet = wallet;
        this.allWalletsId = walletService.getAllWalletsId();
    }

    @GetMapping("/show_wallets")
    public String selectWallet(Model model){
        Wallet emptyWallet = new Wallet();
        // #TODO odświezyć ceny
        if (allWalletsId.isEmpty()){
            model.addAttribute("emptyWallet", emptyWallet);
            return "create_wallet";
        }else{
        model.addAttribute("emptyWallet", emptyWallet);
        model.addAttribute("allWalletsId", allWalletsId);
        // #TODO jeśli mamy jeden wallet id w set to dodać if() i przekierować odrazu na "wallet"
        return "wallet_first_view";
        }
    }

    @GetMapping("/add_new_wallet")
    public String showCreateWalletForm(Model model){
        Wallet emptyWallet = new Wallet();
        model.addAttribute("emptyWallet", emptyWallet);
        return "create_wallet";
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
            model.addAttribute("allWalletsId", allWalletsId);
            return "wallet";
        }

    }

    @GetMapping("/wallet/{id}")
    public String getWalletById(@PathVariable("id") String walletId, Model model){
        Wallet walletById = walletService.findWalletById(walletId);
        Set<ActiveTransaction> activeTransactions = walletById.getActiveTransactions();
        model.addAttribute("walletById", walletById);
        model.addAttribute("activeTransactions", activeTransactions);
        model.addAttribute("allWalletsId", allWalletsId);
        return "wallet";
    }
}
