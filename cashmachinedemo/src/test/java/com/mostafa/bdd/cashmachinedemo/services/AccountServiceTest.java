package com.mostafa.bdd.cashmachinedemo.services;

import com.mostafa.bdd.cashmachinedemo.entities.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void createAccountTest() {
        Account account = new Account();
        account.setBalance(new BigDecimal("100"));
        Account result = accountService.createAccount(account);
        assertNotEquals(0, result.getId());
        assertEquals(account.getBalance(), result.getBalance());
    }

    @Test
    public void getAccountTest() {
        Account account = new Account();
        account.setBalance(new BigDecimal("100"));
        Account result = accountService.createAccount(account);
        Optional<Account> searchAccount = accountService.getAccount(result.getId());
        searchAccount.orElseThrow();
        assertEquals(result, searchAccount.get());
    }

    @Test
    public void updateAccountTest() {
        Account account = new Account();
        account.setBalance(new BigDecimal("100"));
        Account result = accountService.createAccount(account);
        Optional<Account> searchAccount = accountService.getAccount(result.getId());
        searchAccount.orElseThrow();
        Account createdAccount = searchAccount.get();
        createdAccount.setCardNo("123");
        Account updatedAccount = accountService.updateAccount(createdAccount.getId(), createdAccount);
        assertEquals(createdAccount.getCardNo(), updatedAccount.getCardNo());
    }
}
