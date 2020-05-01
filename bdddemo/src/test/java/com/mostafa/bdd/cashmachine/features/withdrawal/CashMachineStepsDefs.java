package com.mostafa.bdd.cashmachine.features.withdrawal;

import com.mostafa.bdd.cashmachine.domains.Account;
import com.mostafa.bdd.cashmachine.domains.CashMachine;
import com.mostafa.bdd.cashmachine.domains.CashRequest;
import com.mostafa.bdd.cashmachine.domains.MachineResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CashMachineStepsDefs {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private String ENDPOINT_CREATE_ACCOUNT = "http://localhost:8080/accounts";
    private String ENDPOINT_PATCH_ACCOUNT = "http://localhost:8080/accounts/{id}";
    private String ENDPOINT_CREATE_CASH_MACHINE = "http://localhost:8080/cashMachines";
    private String ENDPOINT_PATCH_CASH_REQUEST = "http://localhost:8080/cashMachines/{id}/dispense";
    private Account account;
    private CashMachine cashMachine;
    private BigDecimal result;

    @Given("^the account balance is (\\d+)$")
    public void initAccountBalance(BigDecimal accountBalance) {
        Response post = given()
                .contentType(ContentType.JSON)
                .body(new Account(accountBalance))
                .when().post(ENDPOINT_CREATE_ACCOUNT);
        assertEquals(200, post.getStatusCode());
        this.account = post.getBody().as(Account.class);
    }

    @And("^the card (.+) is valid$")
    public void initCardNo(String cardNumber) throws Exception {
        account.setCardNo(cardNumber);
        Response patch = given()
                .contentType(ContentType.JSON)
                .body(account)
                .pathParam("id", account.getId())
                .when().patch(ENDPOINT_PATCH_ACCOUNT);
        assertEquals(200, patch.getStatusCode());
        this.account = patch.getBody().as(Account.class);
        assertEquals(cardNumber, this.account.getCardNo());
    }

    @And("^the machine has (\\d+) money$")
    public void initMachineCashAmount(BigDecimal machineCashAmount) {
        Response post = given()
                .contentType(ContentType.JSON)
                .body(new CashMachine(machineCashAmount))
                .when().post(ENDPOINT_CREATE_CASH_MACHINE);
        assertEquals(200, post.getStatusCode());
        this.cashMachine = post.getBody().as(CashMachine.class);
    }

    @When("^the account holder requests (\\d+)$")
    public void requestForWithdraw(BigDecimal requestAmount){
        CashRequest cashRequest = new CashRequest(this.account.getId(), requestAmount);
        Response patch = given()
                .contentType(ContentType.JSON)
                .body(cashRequest)
                .pathParam("id", cashMachine.getId())
                .when().patch(ENDPOINT_PATCH_CASH_REQUEST);
        assertEquals(200, patch.getStatusCode());
        MachineResponse machineResponse = patch.getBody().as(MachineResponse.class);
        this.account = machineResponse.getAccount();
        this.cashMachine = machineResponse.getCashMachine();
        this.result = machineResponse.getResult();
    }

    @Then("^the cashpoint should dispense (\\d+)$")
    public void checkDispenseAmount(BigDecimal dispenseAmount) {
        assertEquals(dispenseAmount, this.result);
    }

    @And("^the account balance should be (\\d+)$")
    public void checkAccountBalance(BigDecimal finalAccountBalance) {
        assertEquals(finalAccountBalance, this.account.getBalance());
    }

    @And("^the card should be returned$")
    public void checkCard4BeingReleased() {
    }
}
