package com.search.flights.library.webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightsSearchResultsPage extends FlightsPage {



    public FlightsSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void selectCarriers(List<String> carriers) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) _driver;
        System.out.println("Carriers size: " + carriers.size());
        for (String carrier : carriers) {
            String elementId = "checkair" + carrier.trim();
            carrier = carrier.replace("\\", "");
            WebDriverWait wait = new WebDriverWait(_driver, 20);
            WebElement carrierWebElement = _driver.findElement(By.id(elementId));
            jse.executeScript("arguments[0].scrollIntoView()", carrierWebElement);

            Actions actions = new Actions(_driver);
            actions.moveToElement(carrierWebElement).click().build().perform();

            Sleeper.SYSTEM_SLEEPER.sleep(new Duration(5, TimeUnit.SECONDS));
        }

    }

    public PersonalDetailsPage findAndClickBookForCheapestFlight() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) _driver;
        jse.executeScript("window.scrollBy(0,400)");
        Sleeper.SYSTEM_SLEEPER.sleep(new Duration(5, TimeUnit.SECONDS));
        List<WebElement> prices = _driver.findElements(By.xpath("//table[@id='load_data']//button//..//span[@class='strong']"));
        List<Integer> pricesInt = new ArrayList<Integer>();
        for (WebElement price : prices) {
            String priceText = price.getText();
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(priceText);
            while (m.find()) {
                int n = Integer.parseInt(m.group());
                pricesInt.add(n);
            }
        }
        int min = pricesInt.get(0);
        for (int i = 1; i < pricesInt.size(); i++) {
                if (pricesInt.get(i) < min)
                    min = pricesInt.get(i);
        }
        //find web booking button with cheapest price
        WebElement minPrice = _driver.findElement(By.xpath("//table[@id='load_data']//button//..//*[contains(text(), '"+ min+"')]//..//button"));
        minPrice.click();
        return PageFactory.initElements(_driver, PersonalDetailsPage.class);
    }
}
