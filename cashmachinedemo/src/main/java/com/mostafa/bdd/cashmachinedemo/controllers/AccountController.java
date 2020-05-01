package com.mostafa.bdd.cashmachinedemo.controllers;

import com.mostafa.bdd.cashmachinedemo.entities.Account;
import com.mostafa.bdd.cashmachinedemo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/accounts")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PatchMapping(value = "/accounts/{id}")
    public Account createAccount(@PathVariable("id") long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @GetMapping("/accounts/{id}")
    public Optional<Account> getAccount(@PathVariable("id") long id) {
        return accountService.getAccount(id);
    }
}
