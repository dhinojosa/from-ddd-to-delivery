package com.xyzcorp.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InsuranceClaimsSteps {

    @Given("an existing customer")
    public void anExistingCustomer() {
        System.out.println("Getting a customer");
    }

    @When("that customer files a claim")
    public void customerFilesAClaim() {
        System.out.println("Filing claim");
    }

    @And("the claim is within {int} days of the auto accident")
    public void claimIsWithinDay(int days) {
        System.out.printf("We have %d days%n", days);
    }

    @Then("the claim will be flagged as {word}")
    public void claimWillBeFlaggedAs(String flag) {
        System.out.printf("We have %s flag", flag);
    }
}
