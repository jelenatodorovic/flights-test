package com.search.flights.library.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver openBrowser(String browser) {
        WebDriver driver = null;
        if(browser.equals("firefox")) {
            String geckoDriverLocation = Driver.class.getResource("/drivers/geckodriver.exe").getPath();
            System.setProperty("webdriver.gecko.driver", geckoDriverLocation);
            ProfilesIni profile = new ProfilesIni();
            FirefoxProfile firebugProfile = profile.getProfile("selenium");
            driver = new FirefoxDriver(firebugProfile);

        }
        else if(browser.equals("chrome")) {
            String chromeDriverLocation = Driver.class.getResource("/drivers/chromedriver.exe").getPath();
            System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
            driver = new ChromeDriver();
        }
        else if(browser.equals("ie")) {
            String ieDriverLocation = Driver.class.getResource("/drivers/IEDriverServer.exe").getPath();
            System.setProperty("webdriver.ie.driver", ieDriverLocation);
            driver = new InternetExplorerDriver();
        }
        else {
            System.err.println("Browser not supported!!");
            return null;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
