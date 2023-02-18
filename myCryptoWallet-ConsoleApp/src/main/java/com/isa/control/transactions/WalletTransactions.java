package com.isa.control.transactions;

import java.util.Set;

public record WalletTransactions(String walletId, Transactions transactions) {
}
