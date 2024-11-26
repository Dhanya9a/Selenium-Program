package com.core.product.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.framework.base.BaseTest;
import com.automation.framework.pageObjects.HomePage;
import com.automation.framework.pageObjects.MenWearPage;
import com.automation.framework.utils.Constants;
import com.automation.framework.utils.PageObjectManager;
import com.automation.framework.utils.TestUtils;

public class MenWearTest extends BaseTest {
	private PageObjectManager pageObjectManager;
    private MenWearPage menWearPage;
    private HomePage homePage;
    @BeforeClass
    public void setUp() throws InterruptedException {
        pageObjectManager = new PageObjectManager(driver);
        homePage=pageObjectManager.getHomePage();
        menWearPage=homePage.clickOnMenMenuItem();
    }
	@Test(priority=1)
	public void menWearPageTitleTest()
	{
		String title=menWearPage.validateMenWearPageTitle();
		Assert.assertEquals(title,Constants.MENS_WEAR_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void getAllJacketsTest() throws InterruptedException, IOException
	{
		menWearPage.getAllJackets();
		TestUtils.attachTextFileToReport(Constants.RESULT_PATH);
	}
    @AfterClass
    public void tearDown() {
    	//driver.close();
        pageObjectManager.resetPageObjects();
    }
}
