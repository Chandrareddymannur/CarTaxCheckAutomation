package com.cartaxcheck.hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;

import static com.cartaxcheck.utils.WebDriverHelper.getWebDriverInstance;

public class CucumberHooks {

    @After("@ui")
    public void afterScenario(Scenario scenario) {
        getWebDriverInstance().quit();
    }
}
