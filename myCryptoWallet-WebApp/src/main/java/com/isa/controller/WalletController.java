package com.isa.controller;

import com.isa.control.Coin;
import com.isa.control.Wallet;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.model.ActiveTransactionDto;
import com.isa.model.ClosedTransactionDto;
import com.isa.model.WalletDto;
import com.isa.service.WalletService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.isa.model.MapperToDto.mapActiveTransactionToActiveTransactionDto;
import static com.isa.model.MapperToDto.mapWalletToWalletDto;

@Controller
public class WalletController {

    private static Logger LOGGER = LoggerFactory.getLogger(WalletController.class);
    private final WalletService walletService;


    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/show_wallets")
    public String selectWallet(Model model) {
        if (walletService.getWallet() == null) {
            model.addAttribute("emptyWallet", new WalletDto());
            return "wallet/create_wallet";
        } else return "redirect:wallet/form";
    }

    @GetMapping("/top_up/wallet")      // z wallet   i z new wallet
    public String topUpWallet() {
        return "wallet/top_up";
    }

    @RequestMapping("/add/amount")
    public String addAmountToWallet( @RequestParam(value = "amount", required = false, defaultValue = "0") Double amount) {
        walletService.topUpWallet(amount);
        return "redirect:/wallet/form";
    }
    @GetMapping("/wallet/withdrawal")
    public String withdrawalFounds(){return "wallet/withdrawal_form";}

    @RequestMapping("/wallet/withdrawal/submit")
    public String withdrawalFoundsFromWallet(@RequestParam(value = "amount", required = false, defaultValue = "0") Double amount,
                                             Model model){
        if (walletService.checkIsPossibleToWithdrawalAmount(amount)) {
            walletService.withdrawalFoundsFromWallet(amount);
            model.addAttribute("confirm", "withdrawal.confirmation");
        }else {
            model.addAttribute("error", "withdrawal.error");
        }
        return "wallet/withdrawal_Confirmation";
    }

    @PostMapping("/new_wallet")             // z create wallet
    public String createNewWallet(@Valid @ModelAttribute WalletDto walletDto, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("emptyWallet", new WalletDto());
            return "/wallet/create_wallet";
        }
        String id = walletDto.getWalletId();
        double walletBalance = walletDto.getWalletBalance();

        Wallet wallet = new Wallet(id);
        wallet.setPaymentCalc(walletBalance);
        wallet.updateWallet();
        walletService.setWallet(wallet);
        return "redirect:/wallet/form";

    }

    @GetMapping("/wallet/form")
    public String redirectToWalletForm(Model model) {
        walletService.saveWalletToFile();
        Set<ActiveTransactionDto> activeTransactionsDto = walletService.mapActiveTransactionsToDto();
        WalletDto walletDto = mapWalletToWalletDto(walletService.getWallet());
        model.addAttribute("walletById", walletDto);
        model.addAttribute("activeTransactions", activeTransactionsDto);
        return "wallet/wallet";
    }

    @GetMapping("/history/transactions")       // z wallet.html
    public String showTransactionsHistory(Model model) {
        Set<ClosedTransactionDto> transactionsHistoryDto = walletService.mapClosedTransactionsToDto();
        model.addAttribute("history", transactionsHistoryDto);
        return "wallet/transaction_history";
    }

    @RequestMapping(value = "/search/coin", method = RequestMethod.POST)        // z choice coin to buy
    public String searchCoinForBuy(@ModelAttribute("emptyCoin") Coin coin) {
        walletService.searchCoin(coin.getSymbol());
        return "redirect:/buy/coin/form";
    }

    @GetMapping("/buy/coin/form")
    public String redirectToBuyCoinForm(Model model) {
        Coin coin = new Coin();
        List<Coin> searchResult = walletService.getSearchResult();
        model.addAttribute("emptyCoin", coin);
        model.addAttribute("result", searchResult);

        return "wallet/choice_coin_to_buy";
    }

    @GetMapping("/buy/{coinSymbol}")                // z choice coin to buy
    public String establishCoinForBuy(@PathVariable("coinSymbol") String coinSymbol, Model model) {
        if (walletService.getSearchResult().isEmpty()) {
            return "redirect:/buy/coin/form";
        } else {
            walletService.addCoinForBuy(coinSymbol);
            Coin coinForBuy = walletService.getCoinForBuy();
            double walletBalance = walletService.getWallet().getWalletBalance();
            ActiveTransactionDto activeTransactionDto = new ActiveTransactionDto();
            model.addAttribute("coinForBuy", coinForBuy);
            model.addAttribute("emptyTransaction", activeTransactionDto);
            model.addAttribute("balance", walletBalance);
            return "wallet/new_transaction";
        }
    }

    @RequestMapping(value = "/add/transaction", method = RequestMethod.POST)            // z new transaction
    public String buyNewCoin(@ModelAttribute("emptyTransaction") ActiveTransactionDto activeTransaction) {
        double volume = activeTransaction.getVolume();
        walletService.buyNewTokenForWallet(walletService.getCoinForBuy(), volume);
        walletService.setCoinForBuy(new Coin());
        walletService.setSearchResult(new ArrayList<>());
        return "redirect:/wallet/form";
    }

    @GetMapping("/close/transaction{transactionId}")        // z wallet
    public String showClosingTransactionForm(@PathVariable("transactionId") long transactionId, Model model) {
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
    public String closeTransaction(@ModelAttribute("closingTransaction") ActiveTransactionDto activeTransaction) {
        double volume = activeTransaction.getVolume();
        walletService.closeTransaction(volume);
        return "redirect:/wallet/form";
    }

    @GetMapping("/sl-tp/transaction{transactionId}")            // z wallet
    public String showSlTpForm(@PathVariable("transactionId") long transactionId, Model model) {
        walletService.searchTransactionForChangeAttributes(transactionId);
        model.addAttribute("slTpTransaction", mapTransactionForChangeAttributes());
        return "wallet/sl_tp";
    }

    @RequestMapping(value = "/transaction/set/sl/tp", method = RequestMethod.POST)              // z sl_tp
    public String setSlAndTP( @ModelAttribute("slTpTransaction") ActiveTransactionDto slTpTransaction, Model model) {
        double stopLoss = slTpTransaction.getStopLoss();
        double takeProfit = slTpTransaction.getTakeProfit();
        double price = walletService.getTransactionForChangeAttributes().getCurrentPrice();

        if(stopLoss > price || (takeProfit < price && takeProfit != 0)) {
            ActiveTransactionDto activeTransactionDto = mapTransactionForChangeAttributes();
            if (stopLoss > price && takeProfit < price && takeProfit != 0) {
                model.addAttribute("slTpTransaction", activeTransactionDto);
                model.addAttribute("slError", "sl.Error");
                model.addAttribute("tpError", "tp.Error");
                return "wallet/sl_tp";
            } else if (stopLoss > price){
                model.addAttribute("slTpTransaction", activeTransactionDto);
                model.addAttribute("slError", "sl.Error");
                return "wallet/sl_tp";
            }else {
                model.addAttribute("slTpTransaction", activeTransactionDto);
                model.addAttribute("tpError", "tp.Error");
                return "wallet/sl_tp";
            }
        }

        walletService.setSlAndTpAlarm(stopLoss, takeProfit);
        return "redirect:/wallet/form";
    }

    @GetMapping("/wallet/refresh")                          // z wallet
    public String refreshWallet() {
        walletService.getWallet().updateWallet();
        return "redirect:/wallet/form";
    }

    @PostMapping("/calculate/costs")
    public String countTransactionCost(@ModelAttribute ActiveTransactionDto transactionDto, Model model){

        model.addAttribute("emptyTransaction",countTransactionCost(transactionDto));
        model.addAttribute("coinForBuy", walletService.getCoinForBuy());
        model.addAttribute("balance", walletService.getWallet().getWalletBalance());
        if (transactionDto.getTransactionCost() >= walletService.getWallet().getWalletBalance()) {
            model.addAttribute("overbalance", "overbalance");
        }
        return "wallet/new_transaction";
    }

    @PostMapping("/calculate/sl-tp")
    public String countTransactionProfitWithSlAndTp(@ModelAttribute ActiveTransactionDto transactionDto, Model model){
        ActiveTransactionDto transactionForChangeAttributesDto = setSlAndTpForTransactionForChangeAttributes(transactionDto);
        double transactionCost = transactionForChangeAttributesDto.getTransactionCost();
        double volume = transactionForChangeAttributesDto.getVolume();

        model.addAttribute("slTpTransaction",transactionForChangeAttributesDto);
        model.addAttribute("slProfit", countProfitForSl(transactionDto, volume, transactionCost));
        model.addAttribute("tpProfit", countProfitForTp(transactionDto, volume, transactionCost));
        return "wallet/sl_tp";
    }

    private ActiveTransactionDto countTransactionCost(ActiveTransactionDto transactionDto){
        double volume = transactionDto.getVolume();
        double currentPrice = Double.parseDouble(walletService.getCoinForBuy().getLastPrice());
        transactionDto.setTransactionCost(volume * currentPrice);
        return transactionDto;
    }

    private double countProfitForSl(ActiveTransactionDto activeTransactionDto, double volume, double transactionCost){
        double stopLoss = activeTransactionDto.getStopLoss();
        if (stopLoss == 0) return 0;
        else return (stopLoss * volume) - transactionCost;
    }

    private double countProfitForTp(ActiveTransactionDto activeTransactionDto, double volume, double transactionCost){
        double takeProfit = activeTransactionDto.getTakeProfit();
        if (takeProfit == 0) return 0;
        else return (takeProfit * volume) - transactionCost;
    }
    private ActiveTransactionDto mapTransactionForChangeAttributes(){
        return mapActiveTransactionToActiveTransactionDto(walletService.getTransactionForChangeAttributes());
    }
    private ActiveTransactionDto setSlAndTpForTransactionForChangeAttributes(ActiveTransactionDto transactionDto){
        double stopLoss = transactionDto.getStopLoss();
        double takeProfit = transactionDto.getTakeProfit();
        ActiveTransactionDto transactionForChangeAttributesDto = mapTransactionForChangeAttributes();
        transactionForChangeAttributesDto.setStopLoss(stopLoss);
        transactionForChangeAttributesDto.setTakeProfit(takeProfit);
        return transactionForChangeAttributesDto;
    }
}


