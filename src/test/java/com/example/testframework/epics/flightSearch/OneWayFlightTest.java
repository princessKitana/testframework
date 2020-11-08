package com.example.testframework.epics.flightSearch;

import com.example.testframework.core.driverManager.DriverManager;
import com.example.testframework.core.driverManager.DriverManagerFactory;
import com.example.testframework.core.enums.DriverType;
import com.example.testframework.core.pageObjects.FlightPage;
import com.example.testframework.core.pageObjects.ResultPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Tag("regression")
@Story("Ability to search one way flights")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OneWayFlightTest {
    DriverManager driverManager;
    WebDriver webDriver;
    String URL = "https://www.united.com/en/us/";

    @BeforeAll
    public void setUp() {
        driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
        webDriver = driverManager.getDriver();
    }

    @ParameterizedTest
    @CsvSource({"NYC,MIA"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Search flight from New York to Miami")
    public void searchForFlightTest(String origin, String destination) {
        webDriver.get(URL);
        Assertions.assertEquals("United Airlines - Airline Tickets, Travel Deals and Flights", webDriver.getTitle());

        FlightPage flightPage = PageFactory.initElements(webDriver, FlightPage.class);
        flightPage.fillData(origin, destination, "Aug 20");

        ResultPage resultPage = PageFactory.initElements(webDriver, ResultPage.class);
        resultPage.waitForResults();
        resultPage.showAllResults();
        resultPage.sortByEconomyLowest();
        resultPage.takeScreenshot();
        resultPage.getFlightTable();
    }

    @AfterAll
    public void tearDown() {
        driverManager.quitDriver();
    }
}
