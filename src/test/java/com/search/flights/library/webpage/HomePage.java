package com.search.flights.library.webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {

    @FindBy(xpath = "//i[@class='fa fa-plane']")
    private WebElement flightsButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public FlightsPage clickOnFlights() {
        flightsButton.click();
        return PageFactory.initElements(_driver, FlightsPage.class);
    }
}
