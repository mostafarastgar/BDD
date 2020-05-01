package com.mostafa.bdd.cashmachinedemo.services;

import com.mostafa.bdd.cashmachinedemo.cache.LocalCache;
import com.mostafa.bdd.cashmachinedemo.entities.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    Logger logger = LoggerFactory.getLogger(AccountService.class);

    public Account createAccount(Account account) {
        account.setId(LocalCache.idGenerator.incrementAndGet());
        LocalCache.accounts.put(account.getId(), account);
        logger.info("a new account with id:{} was add ", account.getId());
        return account;
    }

    public Optional<Account> getAccount(long id){
        return Optional.of(LocalCache.accounts.get(id));
    }

    public Account updateAccount(long id, Account account){
        Optional<Account> oldAccount = getAccount(id);
        oldAccount.orElseThrow();
        if(account.getBalance() != null){
            oldAccount.get().setBalance(account.getBalance());
        }
        if(account.getCardNo() != null){
            oldAccount.get().setCardNo(account.getCardNo());
        }
        return oldAccount.get();
    }
}
