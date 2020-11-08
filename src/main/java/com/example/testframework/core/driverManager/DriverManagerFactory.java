package com.example.testframework.core.driverManager;

import com.example.testframework.core.enums.DriverType;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(DriverType type) {
        DriverManager driverManager;
        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case IE:
                driverManager = new IEDriverManager();
                break;
            default:
                System.err.println("browser : " + type + " is invalid, Launching Chrome as default browser of choice..");
                driverManager= new ChromeDriverManager();
        }

        return driverManager;
    }
}
