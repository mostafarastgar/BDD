package com.mostafa.bdd.cashmachinedemo.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CashMachine {
    private long id;
    private BigDecimal availableMoney;
}
