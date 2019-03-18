package com.search.flights.test;

import com.search.flights.library.model.TripType;
import com.search.flights.library.util.Driver;
import com.search.flights.library.util.PropertiesUtil;
import com.search.flights.library.webpage.FlightsPage;
import com.search.flights.library.webpage.FlightsSearchResultsPage;
import com.search.flights.library.webpage.HomePage;
import com.search.flights.library.webpage.PersonalDetailsPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    WebDriver driver;
    PropertiesUtil properties;

    HomePage homePage;
    FlightsPage flightsPage;
    FlightsSearchResultsPage flightsSearchResultsPage;
    PersonalDetailsPage personalDetailsPage;

    DateTime departureDate;

    @Before
    public void setUp() {

        properties = PropertiesUtil.getInstance();
        String browser = properties.getValue("browser");
        driver = Driver.openBrowser(browser);

        String url = properties.getValue("url");
        driver.get(url);
    }

    @Given("^I visit (.)*/$")
    public void i_visit(String travels) throws Throwable {
        homePage = new HomePage(driver);
    }

    @Given("^I click Flights$")
    public void i_click_Flights() throws Throwable {
        flightsPage = homePage.clickOnFlights();
    }

    @Given("^I select (.*), (.*) - (.*)$")
    public void i_select(String tripType, String from, String to) throws Throwable {
        flightsPage.selectTripType(TripType.valueOf(tripType));
        flightsPage.sendKeysFromInput(from);
        flightsPage.sendKeysToInput(to);
    }

    @Given("^I select departure date (\\d+)$")
    public void i_select_departure_date(int daysFromToday) throws Throwable {
        DateTime dateTime = new DateTime();
        departureDate = dateTime.plusDays(daysFromToday);
        flightsPage.sendKeysDepartDate(departureDate.toString("yyyy-MM-dd"));
    }

    @Given("^I select return date (\\d+)$")
    public void i_select_return_date(int daysFromDeparture) throws Throwable {
        DateTime arrivalDate = departureDate.plusDays(daysFromDeparture);
        flightsPage.sendKeysArrivalDate(arrivalDate.toString("yyyy-MM-dd"));
        Sleeper.SYSTEM_SLEEPER.sleep(new Duration(3, TimeUnit.SECONDS));
    }

    @Given("^I select (\\d+) adult, (\\d+) child$")
    public void i_select(int numberOfAdults, int numberOfChildren) throws Throwable {
        flightsPage.clickGuestLink();
        flightsPage.selectNumberOfAdults(numberOfAdults + "");
        flightsPage.selectNumberOfChildren(numberOfChildren + "");
        flightsPage.clickDoneAddingPassangers();
    }

    @When("^I click Search button$")
    public void i_click_Search_button() throws Throwable {
        flightsSearchResultsPage = flightsPage.clickSearchButton();
    }

    @When("^I filter by (.*)$")
    public void i_filter(String carriers) throws Throwable {
        String[] carrierArray = carriers.split(",");
        List<String> carrierList = Arrays.asList(carrierArray);
        flightsSearchResultsPage.selectCarriers(carrierList);
    }

    @Then("^I click book now on cheapest price$")
    public void i_click_book_now_on_cheapest_price() throws Throwable {
        personalDetailsPage = flightsSearchResultsPage.findAndClickBookForCheapestFlight();
    }

    @Then("^I am taken to booking page$")
    public void i_am_taken_to_booking_page() throws Throwable {
        //assert booking page is displayed
        Assert.assertEquals(true, personalDetailsPage.isDisplayedFirstNameInput());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
