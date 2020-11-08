package com.example.testframework.epics;

import io.qameta.allure.Feature;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.example.testframework.epics.flightSearch")
@Feature("Flight search")
public class FlightSuiteTest {
}
