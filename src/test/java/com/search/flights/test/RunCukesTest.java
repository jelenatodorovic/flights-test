package com.search.flights.test;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "com.search.flights.test",
        plugin = {"pretty", "json:target/cucumber.json"}
)
public class RunCukesTest {

}
