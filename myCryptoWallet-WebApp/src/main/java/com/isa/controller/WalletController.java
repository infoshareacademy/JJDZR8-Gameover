package com.isa.controller;

import com.isa.control.Coin;
import com.isa.control.CoinSearch;
import com.isa.control.Coins;
import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.ClosedTransaction;
import com.isa.service.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class WalletController {

    private final WalletService walletService;
    private final Wallet wallet;
    private Set<String> allWalletsId;
    private List<Coin> coinsList= Coins.getInstance().getCoinList();
    private Wallet walletById1 = new Wallet();
    private  List<Coin> searchResult = new ArrayList<>();

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
            return "wallet/create_wallet";
        }else{
        model.addAttribute("emptyWallet", emptyWallet);
        model.addAttribute("allWalletsId", allWalletsId);
        // #TODO jeśli mamy jeden wallet id w set to dodać if() i przekierować odrazu na "wallet"
        return "wallet/wallet_first_view";
        }
    }

    @GetMapping("/add_new_wallet")
    public String showCreateWalletForm(Model model){
        Wallet emptyWallet = new Wallet();
        model.addAttribute("emptyWallet", emptyWallet);
        return "wallet/create_wallet";
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
            return "wallet/new_wallet";
        }

    }

    @GetMapping("/wallet/{id}")
    public String getWalletById(@PathVariable("id") String walletId, Model model){
        Wallet walletById = walletService.findWalletById(walletId);
        Set<ActiveTransaction> activeTransactions = walletById.getActiveTransactions();
        model.addAttribute("walletById", walletById);
        model.addAttribute("activeTransactions", activeTransactions);
        model.addAttribute("allWalletsId", allWalletsId);
        return "wallet/wallet";
    }
    @GetMapping("/history/transactions/{id}")
    public String showTransactionsHistory(@PathVariable("id") String walletId, Model model){
        Wallet walletById = walletService.findWalletById(walletId);
        Set<ClosedTransaction> transactionsHistory = walletById.getTransactionsHistory();
        model.addAttribute("history",transactionsHistory);
        return "wallet/transaction_history";
    }

    @GetMapping("/buy/coin/{walletId}")     //wallet.html
    public String showBuyNewCoinForm(@PathVariable("walletId") String walletId){
        walletById1 = walletService.findWalletById(walletId);

        return "redirect:/buy/coin/form";
    }

    @RequestMapping(value = "/search/coin", method = RequestMethod.POST)
    public String searchCoinForBuy(@ModelAttribute("emptyCoin") Coin coin){
        CoinSearch coinSearch = new CoinSearch();
        searchResult = coinSearch.search(coin.getSymbol());

        return "redirect:/buy/coin/form";}

    @GetMapping("/buy/coin/form")
    public String redirectToBuyCoinForm(Model model){
        Coin coin = new Coin();
        model.addAttribute("emptyCoin", coin);
        model.addAttribute("wallet", walletById1);
        model.addAttribute("result", searchResult);

        return "/wallet/buy_coin";
    }


}


