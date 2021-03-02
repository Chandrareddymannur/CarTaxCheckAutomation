package com.cartaxcheck.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.cartaxcheck.utils.WebDriverHelper.getWebDriverInstance;
import static com.cartaxcheck.utils.WebDriverHelper.waitForWebElementTobePresent;


public class VehicleDetailsPage {

    @FindBy(xpath = "//dt[text()='Registration']/following-sibling::dd")
    private WebElement registrationNumber;

    @FindBy(xpath = "//dt[text()='Make']/following-sibling::dd")
    private WebElement make;

    @FindBy(xpath = "//dt[text()='Model']/following-sibling::dd")
    private WebElement model;

    @FindBy(xpath = "//dt[text()='Colour']/following-sibling::dd")
    private WebElement colour;

    @FindBy(xpath = "//dt[text()='Year']/following-sibling::dd")
    private WebElement year;

    public VehicleDetailsPage() {
        PageFactory.initElements(getWebDriverInstance(), this);
    }

    public String getVehicleRegistrationNumber() {
        return retrieveText(registrationNumber);
    }

    public String getVehicleModel() {
        return retrieveText(model);
    }

    public String getVehicleColour() {
        return retrieveText(colour);
    }

    public String getVehicleYear() {
        return retrieveText(year);
    }

    public String getVehicleMake() {
        return retrieveText(make);
    }

    private String retrieveText(WebElement webElement) {
        waitForWebElementTobePresent(webElement);
        return webElement.getText();
    }

}
