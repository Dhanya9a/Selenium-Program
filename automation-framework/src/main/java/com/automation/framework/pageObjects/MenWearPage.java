package com.automation.framework.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.framework.utils.Constants;
import com.automation.framework.utils.TestUtils;

public class MenWearPage {
private WebDriver driver;
	
	@FindBy(xpath="//a[@aria-label='Men' and text()='Men']")
	WebElement men;
	
	@FindBy(xpath="//a[@rowid='2' and contains(@href,'golden-state-warriors-men-jackets')]//div[text()='Jackets']")
	WebElement jackets;
	
	@FindBy(xpath="//div[@class='column']/div[contains(@class,'product-card')]")
	List<WebElement> jacketsListInPage;
	
	@FindBy(xpath="(//ul[@class='pagination-list-container']/li[@class='next-page']/a[@aria-disabled='false'])[2]")
	WebElement next;
	
	
	@FindBy(xpath="(//ul[@class='pagination-list-container']/li[@class='next-page disabled'])[2]")
	WebElement next_disabled;
	
	@FindBy(xpath="//div[@class='column']/div[contains(@class,'product-card')]/div/div[@class='product-card-title']")
	List<WebElement> productTitle;
	
	@FindBy(xpath="//span[@class='lowest']/span[@class='price primary']/span[@class='money-value']/span[@class='sr-only']")
	List<WebElement> productPrice;
	
	
	
	
	
	public MenWearPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this); 
	    }
	//Actions:
	public String validateMenWearPageTitle()
	{
		return driver.getTitle();
	}
	
	public void getAllJackets() throws InterruptedException
	{
		
		TestUtils.waitForVisibility(driver, men, 40);
		TestUtils.moveToElementAction(driver, men);
		TestUtils.waitForElementToBeClickable(driver, jackets, 10);
		jackets.click();
		
		
		TestUtils.waitForVisibility(driver,productPrice.get(productPrice.size()-1) , 30);
		List<String> jacketsList = new ArrayList<>();
		  // Loop through paginated pages
        boolean hasNextPage = true;

        while (hasNextPage) {
        	
            // Loop through each jacket and write details to the text file
            for (int i = 0; i < productTitle.size(); i++) {
                String title = (productTitle.size() > i) ? productTitle.get(i).getText() : "No Title";
                String price=(productPrice.size() > i) ? productPrice.get(i).getText() : "No Price";
                jacketsList.add(title+"-----"+price);
            }	
            // Check if there’s a "Next" button for pagination
            try {
                // If the next button is enabled, click to go to the next page
                if (next != null && next.isEnabled() && next.isDisplayed()) {
                	next.click();
                	Thread.sleep(3000);
                	//TestUtils.waitForInvisibility(driver, jacketsOnPage.get(0), 60); // Wait for the page to load completely
                } 
                else{
                    hasNextPage = false; // No more pages to navigate to
                }
            } catch (org.openqa.selenium.NoSuchElementException e) {
                // If the "Next" button is not found, stop pagination
                hasNextPage = false;
            }
        }
        TestUtils.writeToFile(Constants.RESULT_PATH, jacketsList);

	}
}
