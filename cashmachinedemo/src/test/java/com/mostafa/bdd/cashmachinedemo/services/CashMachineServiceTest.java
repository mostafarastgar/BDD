package com.mostafa.bdd.cashmachinedemo.services;

import com.mostafa.bdd.cashmachinedemo.domains.CashRequest;
import com.mostafa.bdd.cashmachinedemo.domains.MachineResponse;
import com.mostafa.bdd.cashmachinedemo.entities.Account;
import com.mostafa.bdd.cashmachinedemo.entities.CashMachine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CashMachineServiceTest {
    @Autowired
    private CashMachineService cashMachineService;

    @Autowired
    private AccountService accountService;

    @Test
    public void testCreateCashMachine(){
        CashMachine cashMachine = new CashMachine();
        cashMachine.setAvailableMoney(new BigDecimal("1000"));
        CashMachine createdCashMachine = cashMachineService.createCashMachine(cashMachine);
        assertNotEquals(0, createdCashMachine.getId());
    }

    @Test
    public void testDispense(){
        CashMachine cashMachine = new CashMachine();
        cashMachine.setAvailableMoney(new BigDecimal("1000"));
        CashMachine createdCashMachine = cashMachineService.createCashMachine(cashMachine);
        Account account = new Account();
        account.setBalance(new BigDecimal("100"));
        Account createdAccount = accountService.createAccount(account);
        CashRequest cashRequest = new CashRequest(createdAccount.getId(), new BigDecimal("50"));
        MachineResponse result = cashMachineService.dispense(createdCashMachine.getId(), cashRequest);
        assertEquals(new BigDecimal("50"), result.getResult());
        assertEquals(new BigDecimal("50"), result.getAccount().getBalance());
        assertEquals(new BigDecimal("950"), result.getCashMachine().getAvailableMoney());
    }

    @Test
    public void testDispenseException(){
        CashMachine cashMachine = new CashMachine();
        cashMachine.setAvailableMoney(new BigDecimal("1000"));
        CashMachine createdCashMachine = cashMachineService.createCashMachine(cashMachine);
        Account account = new Account();
        account.setBalance(new BigDecimal("2000"));
        Account createdAccount = accountService.createAccount(account);
        CashRequest cashRequest = new CashRequest(createdAccount.getId(), new BigDecimal("1500"));
        assertThrows(RuntimeException.class, () -> {
            cashMachineService.dispense(createdCashMachine.getId(), cashRequest);
        });
    }
}
