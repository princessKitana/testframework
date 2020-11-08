package com.example.testframework.core.pageObjects;

import com.example.testframework.core.domain.FlightInfo;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ResultPage {

    @NonNull
    WebDriver webDriver;

    @FindBy(id = "column-ECONOMY")
    private WebElement economyLowest;
    @FindBy(id = "a-results-show-all")
    private WebElement showAll;
    @FindBy(className = "flight-block")
    private List<WebElement> flightsTable;

    @Step("Sort by Economy Lowest")
    public void sortByEconomyLowest() {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", economyLowest);
    }

    @Step("Wait for flights results")
    public void waitForResults() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("column-ECONOMY")));
    }

    @Step("Click on Show All results")
    public void showAllResults() {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", showAll);
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Show fewer flights')]")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("fare-option-economy")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("flight-block-fares-container")));
    }

    @Step("Get flight table")
    public void getFlightTable() {
        List<FlightInfo> dataList = new ArrayList<>();

        for (WebElement webElement : flightsTable) {
            String depart = webElement.findElement(By.className("flight-time-depart")).getText();
            String arrive = webElement.findElement(By.className("flight-time-arrive")).getText();
            String stops = webElement.findElement(By.className("connection-count")).getText();
            String duration = webElement.findElement(By.className("flight-duration")).getText();

            WebElement economyPrice;
            try {
                economyPrice = webElement.findElement(By.className("fare-option-economy"));
            }
            catch(org.openqa.selenium.StaleElementReferenceException ex) {
                economyPrice = webElement.findElement(By.className("fare-option-economy"));
            }
            String price = economyPrice.findElement(By.className("price-point-wrap")).getText();

            if (!price.equals("Not available")) {
                FlightInfo flight = new FlightInfo();
                flight.setArrive(arrive.replaceAll("\n", " "));
                flight.setDepart(depart.replaceAll("\n", " "));
                flight.setStops(stops);
                flight.setDuration(duration);
                flight.setPrice(StringUtils.substringBefore(price, "\n"));
                dataList.add(flight);
            }
        }
        this.printFlightJson(dataList);
    }

    @Step("Print json in console")
    private void printFlightJson(List<FlightInfo> dataList) {
        FlightInfo.printJsonInConsole(dataList);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }
}
