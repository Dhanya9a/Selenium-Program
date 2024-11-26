package com.automation.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.framework.utils.TestUtils;

public class HomePage {
	private WebDriver driver;
	
	@FindBy(xpath="//div[@id='onetrust-close-btn-container']/button")
	WebElement ContinueWithoutDeciding;
	
	@FindBy(xpath="(//div[@role='dialog']/div/div)[1]")
	WebElement closePopup;
	
	@FindBy(xpath="//li[@class='menu-item']/a/span[text()='Shop']")
	WebElement Shop;
	
	@FindBy(xpath="//ul[contains(@id,'nav-dropdown-desktop')]/li/a[contains(@title,'Men')]")
	WebElement Mens;
	
	@FindBy(xpath="//li[@class='menu-item']/a/span[text()='...']")
	WebElement more;
	
	@FindBy(xpath="//li[@class='menu-item']//a[@title='News & Features']")
	WebElement News_Features;
	
	
    // Constructor to initialize WebElements using PageFactory
    public HomePage(WebDriver driver) {
        this.driver = driver;
       PageFactory.initElements(driver, this);  // Initialize the WebElements
    }
	//Actions:
	public String validateHomePageTitle()
	{
		/*
		 * if (ContinueWithoutDeciding != null && ContinueWithoutDeciding.isDisplayed())
		 * { TestUtils.waitForButtonClickable(driver, ContinueWithoutDeciding, 20);
		 * ContinueWithoutDeciding.click(); } else { System.out.
		 * println("ContinueWithoutDeciding button is not found or not displayed."); }
		 * if (closePopup != null && closePopup.isDisplayed()) {
		 * TestUtils.waitForVisibility(driver, closePopup, 30); closePopup.click(); }
		 * else { System.out.
		 * println("ContinueWithoutDeciding button is not found or not displayed."); }
		 */
		
		
		return driver.getTitle();
	}
	
	public MenWearPage clickOnMenMenuItem() throws InterruptedException
	{

		Actions actions = new Actions(driver);
        actions.moveToElement(closePopup).click().build().perform();
		Thread.sleep(5000);
        
		TestUtils.waitForElementToBeClickable(driver, Shop, 40);
		TestUtils.moveToElementAction(driver, Shop);
		TestUtils.waitForElementToBeClickable(driver, Mens, 10);
		Mens.click();
		Thread.sleep(5000);
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		
		return new MenWearPage(driver);
	}
	
	public NewsPage clickOnNewsMenuItem() throws InterruptedException
	{

		Actions actions = new Actions(driver);
        actions.moveToElement(closePopup).click().build().perform();
        
		TestUtils.waitForElementToBeClickable(driver, more, 40);
		TestUtils.moveToElementAction(driver, more);
		TestUtils.waitForElementToBeClickable(driver, News_Features, 20);
		//News_Features.click();
		TestUtils.moveToElementAction(driver, News_Features);
		News_Features.click();
		
		return new NewsPage(driver);
	}
	
}
