package com.mostafa.bdd.cashmachinedemo.services;

import com.mostafa.bdd.cashmachinedemo.cache.LocalCache;
import com.mostafa.bdd.cashmachinedemo.domains.CashRequest;
import com.mostafa.bdd.cashmachinedemo.domains.MachineResponse;
import com.mostafa.bdd.cashmachinedemo.entities.Account;
import com.mostafa.bdd.cashmachinedemo.entities.CashMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CashMachineService {
    Logger logger = LoggerFactory.getLogger(CashMachineService.class);

    @Autowired
    private AccountService accountService;

    public CashMachine createCashMachine(CashMachine cashMachine) {
        cashMachine.setId(LocalCache.idGenerator.incrementAndGet());
        LocalCache.cashMachine.put(cashMachine.getId(), cashMachine);
        logger.info("a new cash machine with id:{} was add ", cashMachine.getId());
        return cashMachine;
    }

    public MachineResponse dispense(long machineId, CashRequest cashRequest) {
        Optional<Account> accountWrapper = accountService.getAccount(cashRequest.getAccountId());
        accountWrapper.orElseThrow();
        Account account = accountWrapper.get();
        CashMachine cashMachine = LocalCache.cashMachine.get(machineId);
        if (account.getBalance().compareTo(cashRequest.getCashAmount()) > 0
                && cashMachine.getAvailableMoney().compareTo(cashRequest.getCashAmount()) > 0) {
            account.setBalance(account.getBalance().subtract(cashRequest.getCashAmount()));
            cashMachine.setAvailableMoney(cashMachine.getAvailableMoney().subtract(cashRequest.getCashAmount()));
            return new MachineResponse(account, cashMachine, cashRequest.getCashAmount());
        } else {
            RuntimeException e = new RuntimeException("cash request exceeded");
            logger.error("cash request exceeded", e);
            throw e;
        }
    }
}
