package com.mostafa.bdd.cashmachine.domains;

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
