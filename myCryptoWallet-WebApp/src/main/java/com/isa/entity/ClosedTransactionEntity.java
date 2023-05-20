package com.isa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Closed_transactions")
public class ClosedTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stamp", nullable = false, unique = true)
    private long idTransaction;

    @Column(name = "is_Active", nullable = false)
    private boolean isActive;

    @Column(name = "volume", nullable = false)
    private double volume;

    @Column(name = "close_Transaction_Date", nullable = false)
    private String closeTransactionDate;

    @Column(name = "open_Price", nullable = false)
    private double openPrice;

    @Column(name = "close_Price", nullable = false)
    private double closePrice;

    @Column(name = "coin_symbol", nullable = false)
    private String coinSymbol;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private WalletEntity walletEntity;
    /*
    @ManyToOne
    @JoinColumn(name = "coin_id", nullable = false)
    private CoinEntity coinEntity;
*/
}
