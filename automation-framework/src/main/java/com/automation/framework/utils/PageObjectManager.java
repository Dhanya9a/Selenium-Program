package com.automation.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.automation.framework.pageObjects.FooterPage;
import com.automation.framework.pageObjects.HomePage;
import com.automation.framework.pageObjects.TicketsPage;


public class PageObjectManager {
	private WebDriver driver;

    // Declare Page Objects
    private HomePage homePage;
    private TicketsPage ticketsPage;
    private FooterPage footerPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
        // Initialize all Page Objects here
        PageFactory.initElements(driver, this); // Initializes the elements only once
    }

    // Getter methods to access the page objects
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }
    // Getter methods to access the page objects
    public TicketsPage getTicketsPage() {
        if (ticketsPage == null) {
        	ticketsPage = new TicketsPage(driver);
        }
        return ticketsPage;
    }
    // Getter methods to access the page objects
    public FooterPage getFooterPage() {
        if (footerPage == null) {
        	footerPage = new FooterPage(driver);
        }
        return footerPage;
    }
    // Optional: Reset page objects when needed (e.g., after each test)
    public void resetPageObjects() {
        homePage = null;
        ticketsPage=null;
        footerPage=null;
    }

}