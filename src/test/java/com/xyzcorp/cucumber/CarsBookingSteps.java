package com.xyzcorp.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CarsBookingSteps {

    @Given("an existing ride customer")
    public void anExistingCustomer() {

    }

    @When("that customer arrives at the {word} airport")
    public void thatCustomerArrivesAtTheTPAAirport(String airportCode) {

    }

    @And("they already have a {string} booked")
    public void theyAlreadyHaveAHiltonBeachsideHotelBooked(String hotelName) {

    }

    @And("they booked a ride on our mobile application")
    public void theyBookedARideOnOurMobileApplication() {
       throw new RuntimeException("Ooops");
    }

    @And("they are waiting at the closet arrival door {word}")
    public void theyAreWaitingAtTheClosetArrivalDoorC(int doorLabel) {

    }

    @Then("we will make a request to ride services with the location")
    public void weWillMakeARequestToRideServicesWithTheLocation() {

    }
}
