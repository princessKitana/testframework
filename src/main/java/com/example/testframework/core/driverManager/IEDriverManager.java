package com.example.testframework.core.driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.concurrent.TimeUnit;

public class IEDriverManager extends DriverManager {

    @Override
    public void createDriver(){
        InternetExplorerOptions options = new InternetExplorerOptions();
        WebDriverManager.iedriver().setup();
        options.introduceFlakinessByIgnoringSecurityDomains();
        options.requireWindowFocus();
        options.setCapability("ie.usePerProcessProxy", "true");
        options.setCapability("requireWindowFocus", "false");
        options.setCapability("ie.browserCommandLineSwitches", "-private");
        options.setCapability("ie.ensureCleanSession", "true");
        options.setCapability("ignoreZoomSetting", true);

        this.driver = new InternetExplorerDriver(options);
        this.driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

}
