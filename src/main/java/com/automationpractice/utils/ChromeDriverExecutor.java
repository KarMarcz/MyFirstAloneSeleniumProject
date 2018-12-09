package com.automationpractice.utils;

import org.apache.commons.lang3.SystemUtils;

class ChromeDriverExecutor {
    private static final String CHROME_DRIVER_LINUX64 = "src/main/resources/drivers/chrome/chromedriver_linux64";
    private static final String CHROME_DRIVER_MAC64 = "src/main/resources/drivers/chrome/chromedriver_mac64";
    private static final String CHROME_DRIVER_WIN32 = "src/main/resources/drivers/chrome/chromedriver_win32.exe";

    static String getPathToDriver() {
        if(SystemUtils.IS_OS_WINDOWS){
            System.out.println("Windows Detected");
            return CHROME_DRIVER_WIN32;
        }
        if(SystemUtils.IS_OS_LINUX){
            System.out.println("Linux Detected");
            return CHROME_DRIVER_LINUX64;
        }
        if(SystemUtils.IS_OS_MAC){
            System.out.println("Mac Detected");
            return CHROME_DRIVER_MAC64;
        }
        return null;
    }
}
