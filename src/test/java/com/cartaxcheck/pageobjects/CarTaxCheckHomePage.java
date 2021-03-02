package com.cartaxcheck.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.cartaxcheck.utils.WebDriverHelper.getWebDriverInstance;
import static com.cartaxcheck.utils.WebDriverHelper.waitForWebElementTobePresent;

public class CarTaxCheckHomePage {

    @FindBy(id = "vrm-input")
    private WebElement enterRegNumber;

    @FindBy(xpath = "//button[text()='Free Car Check']")
    private WebElement freeCarCheck;

    public CarTaxCheckHomePage() {
        PageFactory.initElements(getWebDriverInstance(), this);
    }

    public void enterAndSearchRegistrationNumber(String registrationNumber) throws InterruptedException {
        enterRegistrationNumber(registrationNumber);
        clickOnFreeCarCheckButton();
    }

    private void enterRegistrationNumber(String regNumber) {
        waitForWebElementTobePresent(enterRegNumber);
        enterRegNumber.clear();
        enterRegNumber.sendKeys(regNumber);
    }

    private void clickOnFreeCarCheckButton() {
        waitForWebElementTobePresent(freeCarCheck);
        freeCarCheck.click();
    }

    public void navigateToCarTaxCheckWebSite(){
        getWebDriverInstance().navigate().to("https://cartaxcheck.co.uk/");
    }

}
