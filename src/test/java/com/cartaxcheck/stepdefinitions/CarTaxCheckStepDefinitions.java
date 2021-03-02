package com.cartaxcheck.stepdefinitions;

import com.cartaxcheck.model.Vehicle;
import com.cartaxcheck.pageobjects.CarTaxCheckHomePage;
import com.cartaxcheck.pageobjects.VehicleDetailsPage;
import com.cartaxcheck.utils.CsvReaderUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

import static com.cartaxcheck.utils.WebDriverHelper.navigateBack;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CarTaxCheckStepDefinitions {

    private CarTaxCheckHomePage carTaxCheckHomePage;
    private VehicleDetailsPage vehicleDetailsPage;
    private List<String> inputVehicleRegNumbersList;
    private Map<String, Vehicle> outputVehicleDetailsList;

    // Pico container is used for dependency injection here. We can also use frameworks like spring IOC, Google guice etc
    public CarTaxCheckStepDefinitions(CarTaxCheckHomePage carTaxCheckHomePage, VehicleDetailsPage vehicleDetailsPage) {
        this.carTaxCheckHomePage = carTaxCheckHomePage;
        this.vehicleDetailsPage = vehicleDetailsPage;
    }

    @Given("^I have vehicle registration numbers list in the \"([^\"]*)\"$")
    public void vehicleRegistrationNumbersListInThe(String filePath) throws Throwable {
        String fileContent = CsvReaderUtils.readInputFileAsString(filePath);
        inputVehicleRegNumbersList = CsvReaderUtils.getVehicleRegistrationNumbers(fileContent);
    }

    @When("^I have expected vehicle details in the \"([^\"]*)\"$")
    public void expectedVehicleDetailsInThe(String outputFile) throws Throwable {
        outputVehicleDetailsList = CsvReaderUtils.getExpectedVehicleDetailsFromCSV(outputFile);
    }

    @Then("^Expected vehicle details should match with Actual details on the website$")
    public void expectedVehicleDetailsShouldMatchWithActualDetailsOnTheWebsite() throws Throwable {
        verifyVehicleDetails(outputVehicleDetailsList);
    }

    @Then("^I navigate to cartaxcheck website to check vehicle details$")
    public void navigateToCarTaxCheckWebSite() {
        carTaxCheckHomePage.navigateToCarTaxCheckWebSite();
    }

    private void verifyVehicleDetails(Map<String, Vehicle> vehicleDetailsMap) throws Exception {

        for (String vehicleRegistrationNumber : inputVehicleRegNumbersList) {
            Vehicle vehicleDetailsCsv = vehicleDetailsMap.get(vehicleRegistrationNumber);

            if (vehicleDetailsCsv != null) {
                log.info("vehicle data found for Registration Number :  " + vehicleRegistrationNumber);
                carTaxCheckHomePage.enterAndSearchRegistrationNumber(vehicleRegistrationNumber);

//              Asserting Vehicle details
                assertThat(vehicleDetailsPage.getVehicleRegistrationNumber()).as("verify vehicle registration").isEqualTo(vehicleDetailsCsv.getRegistration());
                assertThat(vehicleDetailsPage.getVehicleMake()).as("verify vehicle make").isEqualTo(vehicleDetailsCsv.getMake());
                assertThat(vehicleDetailsPage.getVehicleModel()).as("verify vehicle model").isEqualTo(vehicleDetailsCsv.getModel());
                assertThat(vehicleDetailsPage.getVehicleColour()).as("verify vehicle colour").isEqualTo(vehicleDetailsCsv.getColour());
                assertThat(vehicleDetailsPage.getVehicleYear()).as("verify vehicle year").isEqualTo(vehicleDetailsCsv.getYear());

            } else {
                log.error("No vehicle data found for Registration Number :  " + vehicleRegistrationNumber);
            }

            if(vehicleRegistrationNumber!= null) {
                //Navigate back to home page
                navigateBack();
            }
        }

    }

}
