package com.isa.controller;

import com.isa.control.Coin;
import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.model.ActiveTransactionDto;
import com.isa.model.ClosedTransactionDto;
import com.isa.model.WalletDto;
import com.isa.service.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.isa.model.MapperToDto.mapActiveTransactionToActiveTransactionDto;
import static com.isa.model.MapperToDto.mapWalletToWalletDto;

@Controller
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping("/show_wallets")
    public String selectWallet(Model model){
        Wallet emptyWallet = new Wallet();
        // #TODO odświezyć ceny
        if (walletService.getWallet() == null ){
            model.addAttribute("emptyWallet", emptyWallet);
            return "wallet/create_wallet";
        }else return "redirect:wallet/form";
    }

    @GetMapping("/top_up/wallet")      // z wallet   i z new wallet
    public String topUpWallet(Model model){
        Wallet emptyWallet = new Wallet();
        model.addAttribute("emptyWallet", emptyWallet);
        return "wallet/top_up";
    }

    @RequestMapping("/add/amount")
    public String addAmountToWallet(@RequestParam(value = "amount", required = false) double amount){
        walletService.TopUpWallet(amount);
        return "redirect:/wallet/form";
    }

    @PostMapping("/new_wallet")             // z create wallet
    public String createNewWallet(@ModelAttribute Wallet wallet){       // #TODO dodać walidację
        String id = wallet.getWalletId();
        double walletBalance = wallet.getWalletBalance();
        if (id.isEmpty() || walletBalance < 0) return "wallet/create_wallet";
        else{
            wallet.updateWallet();
            walletService.setWallet(wallet);
            return "redirect:/wallet/form";
        }
    }

    @GetMapping("/wallet/form")
    public String redirectToWalletForm(Model model){
        walletService.getWallet().updateWallet();
        walletService.saveWalletToFile();
        Set<ActiveTransactionDto> activeTransactionsDto = walletService.mapActiveTransactionsToDto();
        WalletDto walletDto = mapWalletToWalletDto(walletService.getWallet());
        model.addAttribute("walletById", walletDto);
        model.addAttribute("activeTransactions", activeTransactionsDto);
        return "wallet/wallet";
    }
    @GetMapping("/history/transactions")       // z wallet.html
    public String showTransactionsHistory(Model model){
        Set<ClosedTransactionDto> transactionsHistoryDto = walletService.mapClosedTransactionsToDto();
        model.addAttribute("history",transactionsHistoryDto);
        return "wallet/transaction_history";
    }

    @RequestMapping(value = "/search/coin", method = RequestMethod.POST)        // z choice coin to buy
    public String searchCoinForBuy(@ModelAttribute("emptyCoin") Coin coin){
        walletService.searchCoin(coin.getSymbol());
        return "redirect:/buy/coin/form";}

    @GetMapping("/buy/coin/form")
    public String redirectToBuyCoinForm(Model model){
        Coin coin = new Coin();
        List<Coin> searchResult = walletService.getSearchResult();
        model.addAttribute("emptyCoin", coin);
        model.addAttribute("result", searchResult);

        return "wallet/choice_coin_to_buy";
    }

    @GetMapping("/buy/{coinSymbol}")                // z choice coin to buy
    public String establishCoinForBuy(@PathVariable("coinSymbol") String coinSymbol, Model model){
        if (walletService.getSearchResult().isEmpty()){
            return "redirect:/buy/coin/form";
        }else {
            walletService.addCoinForBuy(coinSymbol);
            Coin coinForBuy = walletService.getCoinForBuy();
            ActiveTransaction activeTransaction = new ActiveTransaction();
            model.addAttribute("coinForBuy", coinForBuy);
            model.addAttribute("emptyTransaction", activeTransaction);
            return "wallet/new_transaction";
        }
    }

    @RequestMapping(value = "/add/transaction", method = RequestMethod.POST)            // z new transaction
    public String buyNewCoin(@ModelAttribute("emptyTransaction") ActiveTransaction activeTransaction){
        double volume = activeTransaction.getVolume();
        walletService.buyNewTokenForWallet(walletService.getCoinForBuy(), volume);
        walletService.setCoinForBuy(new Coin());
        walletService.setSearchResult(new ArrayList<>());
        return "redirect:/wallet/form";
    }

    @GetMapping("/close/transaction{transactionId}")        // z wallet
    public String showClosingTransactionForm(@PathVariable("transactionId") long transactionId, Model model){
        walletService.searchTransactionForClose(transactionId);
        ActiveTransaction transactionForClose = walletService.getTransactionForClose();
        if (transactionForClose.getCoin() == null) return "wallet/mistake_form";
        else {
            ActiveTransactionDto transactionForCloseDto = mapActiveTransactionToActiveTransactionDto(transactionForClose);
            model.addAttribute("closingTransaction", transactionForCloseDto);
            return "wallet/close_transaction";
        }
    }

    @RequestMapping(value = "/transaction/close", method = RequestMethod.POST)          // z close_transaction
    public String closeTransaction(@ModelAttribute("closingTransaction") ActiveTransaction activeTransaction){
        double volume = activeTransaction.getVolume();
        walletService.closeTransaction(volume);
        return "redirect:/wallet/form";
    }

    @GetMapping("/sl-tp/transaction{transactionId}")            // z wallet
    public String showSlTpForm(@PathVariable("transactionId") long transactionId, Model model){
        walletService.searchTransactionForChangeAttributes(transactionId);
        ActiveTransaction transactionForChangeAttributes = walletService.getTransactionForChangeAttributes();
        ActiveTransactionDto activeTransactionDto = mapActiveTransactionToActiveTransactionDto(transactionForChangeAttributes);
        model.addAttribute("slTpTransaction", activeTransactionDto);
        return "wallet/sl_tp";
    }

    @RequestMapping(value = "/transaction/set/sl/tp", method = RequestMethod.POST)              // z sl_tp
    public String setSlAndTP(@ModelAttribute("slTpTransaction") ActiveTransaction slTpTransaction){
        double stopLoss = slTpTransaction.getStopLoss();
        double takeProfit = slTpTransaction.getTakeProfit();
        walletService.setSlAndTpAlarm(stopLoss, takeProfit);
        return "redirect:/wallet/form";
    }

    @GetMapping("/wallet/refresh")                          // z wallet
    public String refreshWallet(){
        walletService.getWallet().updateWallet();
        return "redirect:/wallet/form";
    }


}


