package com.mostafa.bdd.cashmachine.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Account {
    private long id;
    private BigDecimal balance;
    private String cardNo;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }
}
