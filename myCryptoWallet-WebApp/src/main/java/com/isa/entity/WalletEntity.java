package com.isa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "wallet", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class WalletEntity {


    @Id
    private String walletId;

    @Column(name = "wallet_sum")
    private double walletSum;
    @Column(name = "profit_loss")
    private double profitLoss;
    @Column(name = "historical_profit_loss")
    private double historicalProfitLoss;
    @Column(name = "transatcion_costs")
    private double transactionCosts;
    @PositiveOrZero
    private double walletBalance;

    /*@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users",
            joinColumns = @JoinColumn(
                    name = "walletid_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "wallet_id", referencedColumnName = "wallet_id")
            )*/
}


