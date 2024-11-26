package com.core.product.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.framework.base.BaseTest;
import com.automation.framework.pageObjects.HomePage;
import com.automation.framework.pageObjects.MenWearPage;
import com.automation.framework.utils.Constants;
import com.automation.framework.utils.PageObjectManager;

public class HomePageTest extends BaseTest {
	private PageObjectManager pageObjectManager;
    private MenWearPage menWearPage;
    private HomePage hp;
    @BeforeClass
    public void setUp() {
        pageObjectManager = new PageObjectManager(driver);
		hp=pageObjectManager.getHomePage();
    }
	@Test(priority=1)
	public void homePageTitleTest()
	{
		String title=hp.validateHomePageTitle();
		Assert.assertTrue(title.contains(Constants.HOME_PAGE_TITLE));
	}

	
    @AfterClass
    public void tearDown() {
    	//driver.close();
        pageObjectManager.resetPageObjects();
    }
}
