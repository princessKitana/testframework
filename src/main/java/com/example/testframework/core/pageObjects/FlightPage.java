package com.example.testframework.core.pageObjects;

import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RequiredArgsConstructor
public class FlightPage {

    @NonNull
    WebDriver webDriver;

    @FindBy(id = "oneway")
    private WebElement oneWay;
    @FindBy(id = "bookFlightOriginInput")
    private WebElement flightOrigin;
    @FindBy(id = "bookFlightDestinationInput")
    private WebElement flightDestination;
    @FindBy(id = "DepartDate")
    private WebElement departDate;
    @FindBy(id = "cabinType")
    private WebElement cabinType;
    @FindBy(xpath = "//*[@id=\"bookFlightForm\"]/div[4]/div/div[1]/div/div/button")
    private WebElement submit;

    @Step("Enter input data")
    public void fillData(String origin, String destination, String date) {
        this.selectOneWay();
        this.enterFlightOrigin(origin);
        this.enterFlightDestination(destination);
        this.enterDepartDate(date);
        this.selectCabinType();
        this.submit();
    }

    @Step("Select One way")
    public void selectOneWay() {
        oneWay.click();
    }

    @Step("Fill origin")
    public void enterFlightOrigin(String origin) {
        flightOrigin.sendKeys(Keys.CONTROL,"a");
        flightOrigin.sendKeys(origin);
    }

    @Step("Fill destination")
    public void enterFlightDestination(String destination) {
        flightDestination.sendKeys(destination);
    }

    @Step("Fill depart date")
    public void enterDepartDate(String date) {
        departDate.clear();
        departDate.sendKeys(Keys.CONTROL,"a");
        departDate.sendKeys(date);
    }

    @Step("Fill destination")
    public void selectCabinType() {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", cabinType);
        cabinType.sendKeys(Keys.ARROW_DOWN);
        cabinType.sendKeys(Keys.ENTER);
    }

    @Step("Press Find Flights")
    public void submit() {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", submit);
    }

}
