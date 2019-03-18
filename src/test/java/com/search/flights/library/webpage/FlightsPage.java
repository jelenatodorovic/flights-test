package com.search.flights.library.webpage;

import com.search.flights.library.model.TripType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

public class FlightsPage extends Page {

    @FindBy(xpath = "//div[@id = 's2id_location_from']//input")
    private WebElement fromInput;

    @FindBy(xpath = "//div[@id = 's2id_location_to']//input")
    private WebElement toInput;

    @FindBy(xpath = "//input[@name='departure']")
    private WebElement departDateInput;

    @FindBy(xpath = "//input[@name='arrival']")
    private WebElement arrivalDateInput;

    @FindBy(id = "oneway")
    private WebElement oneWayRadioButton;

    @FindBy(xpath = "//input[@id='round']")
    private WebElement roundRadioButton;

    @FindBy(xpath = "//input[@name='totalManualPassenger']")
    private WebElement guestsLink;

    @FindBy(xpath = "//select[@name='madult']")
    private WebElement adultsDropdown;

    @FindBy(xpath = "//select[@name='mchildren']")
    private WebElement childDropdown;

    @FindBy(xpath = "//button[@id='sumManualPassenger']")
    private WebElement doneAddPassangersButton;

    @FindBy(xpath = "//button[@class='btn-primary btn btn-lg btn-block pfb0']")
    private WebElement searchButton;

    public FlightsPage(WebDriver driver) {
        super(driver);
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id = 's2id_location_from']//input")));
    }

    public void sendKeysFromInput(String from) throws InterruptedException {
        fromInput.sendKeys(from);
        new WebDriverWait(_driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul//li//span[@class='select2-match']")));
        WebElement dropdownElement = _driver.findElement(By.xpath("//ul//li//span[@class='select2-match']"));
        dropdownElement.click();
        Sleeper.SYSTEM_SLEEPER.sleep(new Duration(3, TimeUnit.SECONDS));
    }

    public void sendKeysToInput(String to) throws InterruptedException {
        toInput.sendKeys(to);
        new WebDriverWait(_driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul//li//span[@class='select2-match']")));
        WebElement dropdownElement = _driver.findElement(By.xpath("//ul//li//span[@class='select2-match']"));
        dropdownElement.click();
    }

    public void sendKeysDepartDate(String departDate) throws InterruptedException {
        departDateInput.sendKeys(departDate);
    }

    public void sendKeysArrivalDate(String arrivalDate) {
        arrivalDateInput.sendKeys(arrivalDate);
    }

    public void selectOneWay() {
        if (!oneWayRadioButton.isSelected()) {
            JavascriptExecutor jse = (JavascriptExecutor) _driver;
            jse.executeScript("arguments[0].click();", oneWayRadioButton);
        }
    }

    public void selectRound() {
        WebElement round = _driver.findElement(By.xpath("//div[@class='iradio_square-grey']"));
        round.click();
    }

    public void clickGuestLink() throws InterruptedException {
        guestsLink.click();
        Sleeper.SYSTEM_SLEEPER.sleep(new Duration(3, TimeUnit.SECONDS));
    }

    public void selectTripType(Enum tripType) throws InterruptedException {
        if (tripType == TripType.ONEWAY)
            selectOneWay();
        else
            selectRound();
        Sleeper.SYSTEM_SLEEPER.sleep(new Duration(3, TimeUnit.SECONDS));
    }

    public void selectNumberOfAdults(String number) {
        Select select = new Select(adultsDropdown);
        select.selectByValue(number);
    }

    public void selectNumberOfChildren(String number) {
        Select select = new Select(childDropdown);
        select.selectByValue(number);
    }

    public void clickDoneAddingPassangers() {
        new WebDriverWait(_driver, 20).until(ExpectedConditions.elementToBeClickable(doneAddPassangersButton));
        doneAddPassangersButton.click();
    }

    public FlightsSearchResultsPage clickSearchButton() {
        new WebDriverWait(_driver, 20).until(ExpectedConditions.elementToBeClickable(searchButton));
        JavascriptExecutor jse = (JavascriptExecutor) _driver;
        jse.executeScript("arguments[0].click();", searchButton);

        return PageFactory.initElements(_driver, FlightsSearchResultsPage.class);
    }

}
