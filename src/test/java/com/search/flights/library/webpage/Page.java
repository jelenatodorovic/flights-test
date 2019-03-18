package com.search.flights.library.webpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Page {

    protected WebDriver _driver;

    public Page(WebDriver driver) {
        _driver = driver;
        PageFactory.initElements(_driver, this);

    }
}
