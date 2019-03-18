package com.search.flights.library.webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalDetailsPage extends Page {

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstnameInput;

    public PersonalDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayedFirstNameInput() {
        return firstnameInput.isDisplayed();
    }
}
