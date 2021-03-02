package com.cartaxcheck.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelper {

    private static WebDriver driver;

    private static final int WAIT_TIME = 3;

    private WebDriverHelper() {}

    public static WebDriver getWebDriverInstance() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir")
                            + "/src/test/resources/tools/chromedriver");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void waitForWebElementTobePresent(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void navigateBack(){
        driver.navigate().back();
    }
}
