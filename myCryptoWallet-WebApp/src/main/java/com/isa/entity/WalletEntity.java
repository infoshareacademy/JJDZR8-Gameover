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
@Table(name = "wallet")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "wallet_name")
    private String walletId;

    @Column(name = "historical_profit_loss")
    private double historicalProfitLoss;

    @Column(name="paymentCalc")
    private double paymentCalc;

    @OneToOne()
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    /*@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users",
            joinColumns = @JoinColumn(
                    name = "walletid_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "wallet_id", referencedColumnName = "wallet_id")
            )*/
}


