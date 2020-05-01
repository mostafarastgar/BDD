package com.mostafa.bdd.cashmachinedemo.cache;

import com.mostafa.bdd.cashmachinedemo.entities.Account;
import com.mostafa.bdd.cashmachinedemo.entities.CashMachine;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class LocalCache {
    public static final AtomicLong idGenerator = new AtomicLong(0);
    public static final Map<Long, Account> accounts = new Hashtable<>();
    public static final Map<Long, CashMachine> cashMachine = new Hashtable<>();

    private LocalCache() {

    }
}
