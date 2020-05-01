package com.mostafa.bdd.cashmachinedemo.controllers;

import com.mostafa.bdd.cashmachinedemo.domains.CashRequest;
import com.mostafa.bdd.cashmachinedemo.domains.MachineResponse;
import com.mostafa.bdd.cashmachinedemo.entities.CashMachine;
import com.mostafa.bdd.cashmachinedemo.services.CashMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MachineController {
    @Autowired
    private CashMachineService cashMachineService;

    @PostMapping(value = "/cashMachines")
    public CashMachine createAccount(@RequestBody CashMachine cashMachine) {
        return cashMachineService.createCashMachine(cashMachine);
    }

    @PatchMapping(value = "/cashMachines/{id}/dispense")
    public MachineResponse dispense(@PathVariable("id") long id, @RequestBody CashRequest cashRequest) {
        return cashMachineService.dispense(id, cashRequest);
    }
}
