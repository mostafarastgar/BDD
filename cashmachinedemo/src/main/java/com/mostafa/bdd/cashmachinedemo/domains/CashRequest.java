package com.mostafa.bdd.cashmachinedemo.domains;

import com.mostafa.bdd.cashmachinedemo.entities.Account;
import com.mostafa.bdd.cashmachinedemo.entities.CashMachine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashRequest {
    private long accountId;
    private BigDecimal cashAmount;
}
