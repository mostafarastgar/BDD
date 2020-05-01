package com.mostafa.bdd.cashmachine.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CashMachine {
    private long id;
    private BigDecimal availableMoney;

    public CashMachine(BigDecimal availableMoney) {
        this.availableMoney = availableMoney;
    }
}
