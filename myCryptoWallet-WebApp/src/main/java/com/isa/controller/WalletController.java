package com.isa.controller;

import com.isa.control.Coin;
import com.isa.control.CoinSearch;
import com.isa.control.Coins;
import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.control.transactions.ClosedTransaction;
import com.isa.service.CoinUtils;
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
    private Wallet walletById = new Wallet();
    private  List<Coin> searchResult = new ArrayList<>();
    private Coin coinForBuy = new Coin();
    private ActiveTransaction transactionForClose;
    private ActiveTransaction transactionForChangeAttributes;

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

    @GetMapping("/add_new_wallet")      // z wallet   i z new wallet    , wallet first view
    public String showCreateWalletForm(Model model){
        Wallet emptyWallet = new Wallet();
        model.addAttribute("emptyWallet", emptyWallet);
        return "wallet/create_wallet";
    }

    @PostMapping("/new_wallet")             // z create wallet
    public String createNewWallet(@ModelAttribute Wallet wallet, Model model){       // #TODO dodać walidację
        String id = wallet.getWalletId();
        if (walletService.isNewWalletIdExist(id)) return "wallet/create_wallet";
        else{
            wallet.updateWallet();
            walletService.addWalletToWalletsMap(wallet);
            walletById = walletService.findWalletById(id);
            return "redirect:/wallet/form";
        }

    }

    @GetMapping("/wallet/{id}")             // z wallet   , wall first view
    public String getWalletById(@PathVariable("id") String walletId){
        walletById = walletService.findWalletById(walletId);

        return "redirect:/wallet/form";
    }

    @GetMapping("/wallet/form")
    public String redirectToWalletForm(Model model){
        coinForBuy = new Coin();
        Set<ActiveTransaction> activeTransactions = walletById.getActiveTransactions();
        model.addAttribute("walletById", walletById);
        model.addAttribute("activeTransactions", activeTransactions);
        model.addAttribute("allWalletsId", allWalletsId);
        return "wallet/wallet";
    }
    @GetMapping("/history/transactions/{id}")       // z wallet.html
    public String showTransactionsHistory(@PathVariable("id") String walletId, Model model){
        walletById = walletService.findWalletById(walletId);
        Set<ClosedTransaction> transactionsHistory = walletById.getTransactionsHistory();
        model.addAttribute("history",transactionsHistory);
        return "wallet/transaction_history";
    }

    @GetMapping("/buy/coin/{walletId}")     // z wallet.html
    public String showBuyNewCoinForm(@PathVariable("walletId") String walletId){
        walletById = walletService.findWalletById(walletId);

        return "redirect:/buy/coin/form";
    }

    @RequestMapping(value = "/search/coin", method = RequestMethod.POST)        // z choice coin to buy
    public String searchCoinForBuy(@ModelAttribute("emptyCoin") Coin coin){
        CoinSearch coinSearch = new CoinSearch();
        searchResult = coinSearch.search(coin.getSymbol());

        return "redirect:/buy/coin/form";}

    @GetMapping("/buy/coin/form")
    public String redirectToBuyCoinForm(Model model){
        Coin coin = new Coin();
        model.addAttribute("emptyCoin", coin);
        model.addAttribute("wallet", walletById);
        model.addAttribute("result", searchResult);

        return "wallet/choice_coin_to_buy";
    }

    @GetMapping("/buy/{coinSymbol}")                // z choice coin to buy
    public String establishCoinForBuy(@PathVariable("coinSymbol") String coinSymbol, Model model){
        // #TODO zastąpić metodę wyszukiwania odpowiednim DI
        CoinSearch coinSearch = new CoinSearch();
        searchResult = coinSearch.search(coinSymbol);

        if (searchResult.isEmpty()){
            return "redirect:/buy/coin/form";
        }else {
            coinForBuy = searchResult.get(0);
            searchResult = new ArrayList<>();
            ActiveTransaction activeTransaction = new ActiveTransaction();
            model.addAttribute("coinForBuy", coinForBuy);
            model.addAttribute("emptyTransaction", activeTransaction);
            return "wallet/new_transaction";
        }
    }

    @RequestMapping(value = "/add/transaction", method = RequestMethod.POST)            // z new transaction
    public String buyNewCoin(@ModelAttribute("emptyTransaction") ActiveTransaction activeTransaction){
        double volume = activeTransaction.getVolume();
        walletById.buyNewToken(coinForBuy, volume);
        return "redirect:/wallet/form";
    }

    @GetMapping("/close/transaction{transactionId}")        // z wallet
    public String showClosingTransactionForm(@PathVariable("transactionId") long transactionId, Model model){

        transactionForClose = walletById.searchActiveTransaction(transactionId);
        if (transactionForClose.getCoin() == null) return "wallet/mistake_form";
        else {
            model.addAttribute("closingTransaction", transactionForClose);
            return "wallet/close_transaction";
        }
    }

    @RequestMapping(value = "/transaction/close", method = RequestMethod.POST)          // z close_transaction
    public String closeTransaction(@ModelAttribute("closingTransaction") ActiveTransaction activeTransaction){
        double volume = activeTransaction.getVolume();
        walletById.closeActiveTransaction(transactionForClose, volume);
        transactionForClose = new ActiveTransaction();
        return "redirect:/wallet/form";
    }

    @GetMapping("/sl-tp/transaction{transactionId}")            // z wallet
    public String showSlTpForm(@PathVariable("transactionId") long transactionId, Model model){

        transactionForChangeAttributes = walletById.searchActiveTransaction(transactionId);
        model.addAttribute("slTpTransaction", transactionForChangeAttributes);

        return "wallet/sl_tp";
    }

    @RequestMapping(value = "/transaction/set/sl/tp", method = RequestMethod.POST)              // z sl_tp
    public String setSlAndTP(@ModelAttribute("slTpTransaction") ActiveTransaction slTpTransaction){
        double stopLoss = slTpTransaction.getStopLoss();
        double takeProfit = slTpTransaction.getTakeProfit();

        transactionForChangeAttributes.setSLAlarm(stopLoss, true);
        transactionForChangeAttributes.setTPAlarm(takeProfit, true);
        walletById.getActiveTransactions().removeIf(n -> n.getIdTransaction() == transactionForChangeAttributes.getIdTransaction());
        walletById.getActiveTransactions().add(transactionForChangeAttributes);
        transactionForChangeAttributes = new ActiveTransaction();

        return "redirect:/wallet/form";
    }

    @GetMapping("/wallet/refresh")                          // z wallet
    public String refreshWallet(){
        walletById.updateWallet();
        return "redirect:/wallet/form";
    }


}


