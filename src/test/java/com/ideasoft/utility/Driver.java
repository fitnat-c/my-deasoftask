package com.ideasoft.utility;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    private static final Logger logger = LoggerFactory.getLogger(Driver.class);
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            initializeDriver();
            driverPool.get().manage().window().maximize();

        }
        return driverPool.get();
    }

    private static void initializeDriver() {
        logger.info("===============================================================");
        logger.info("Environment : {}", ConfigReader.getProperty("env"));
        logger.info("Operating System : {}", System.getProperty("os.name"));
        logger.info("Browser: {}", ConfigReader.getProperty("browser"));
        logger.info("===============================================================");


        String browser = System.getProperty("browser", ConfigReader.getProperty("browser"));
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driverPool.set(new ChromeDriver(createChromeOptions()));
                    break;
                case "chrome_headless":
                    driverPool.set(new ChromeDriver(initChromeHeadlessDriver()));
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver(initFirefoxDriver()));
                    break;
                case "firefox_headless":
                    driverPool.set(new FirefoxDriver(initFirefoxHeadlessDriver()));
                    break;

                case "remote_chrome":
                    driverPool.set(initRemoteWebDriver(new ChromeOptions()));
                    break;
                case "remote_firefox":
                    driverPool.set(initRemoteWebDriver(new FirefoxOptions()));
                    break;
                default:
                    throw new UnsupportedOperationException("Browser " + browser + " is not supported.");
            }

        } catch (Exception e) {
            logger.error("Error initializing the driver: {}", e.getMessage(), e);
        }
    }

    private static ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        configureChromeOptions(options);
        return options;
    }

    private static ChromeOptions initChromeHeadlessDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        configureChromeOptions(options);
        return options;
    }

    private static FirefoxOptions initFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile());
        return options;
    }

    private static FirefoxOptions initFirefoxHeadlessDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless=new");
        return options;
    }


    private static WebDriver initRemoteWebDriver(Capabilities capabilities) {
        try {
            return new RemoteWebDriver(new URL("http://localhost:4444//wd/hub"), capabilities);
        } catch (Exception e) {
            logger.error("Failed to initialize remote web driver: {}", e.getMessage(), e);
            return null;
        }
    }

    private static void configureChromeOptions(ChromeOptions options) {
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();

        contentSettings.put("cookies", ConfigReader.getProperty("cookiesEnableDisable"));
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--remote-allow-origins=*");
    }

    public static FirefoxProfile firefoxProfile() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        try {
            firefoxProfile.setPreference("browser.download.folderList", 2);
            firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                    "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
        } catch (Exception e) {
            logger.error("Profile can't be configured for Firefox: {}", e.getMessage(), e);
        }
        return firefoxProfile;
    }

    public static void close() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
