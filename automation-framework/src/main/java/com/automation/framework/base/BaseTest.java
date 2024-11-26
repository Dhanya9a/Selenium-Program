package com.automation.framework.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;

import com.automation.framework.utils.Constants;
import com.automation.framework.utils.PageObjectManager;

public class BaseTest {
	protected WebDriver driver;
    protected PageObjectManager pageObjectManager;

    @BeforeClass
    @Parameters({"browser","base.url"})
    public void setUp(String browser,String baseUrl) {
        // Initialize WebDriver (example for Chrome and Firefox)
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Constants.SCRIPT_TIMEOUT));
        driver.manage().window().maximize();
        driver.get(baseUrl);
        // Initialize PageObjectManager (will initialize all page objects once here)
        pageObjectManager = new PageObjectManager(driver);
    }
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
