package com.core.product.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.framework.base.BaseTest;
import com.automation.framework.pageObjects.HomePage;
import com.automation.framework.pageObjects.MenWearPage;
import com.automation.framework.pageObjects.NewsPage;
import com.automation.framework.utils.Constants;
import com.automation.framework.utils.PageObjectManager;
import com.automation.framework.utils.TestUtils;

public class NewsPageTest extends BaseTest {
	private PageObjectManager pageObjectManager;
    private NewsPage newsPage;
    private HomePage homePage;
    @BeforeClass
    public void setUp() throws InterruptedException {
        pageObjectManager = new PageObjectManager(driver);
        homePage=pageObjectManager.getHomePage();
        newsPage=homePage.clickOnNewsMenuItem();
    }
	@Test(priority=1)
	public void newsPageTitleTest()
	{
		String title=newsPage.validateNewsPageTitle();
		Assert.assertEquals(title,Constants.NEWS_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void getAllVideosTest() throws InterruptedException, IOException
	{
		int count[]=newsPage.countofVideos();
		TestUtils.attachTextToReport("Total Number of videos "+count[0]);
		TestUtils.attachTextToReport("Number of videos posted 3 days back "+count[1]);
	}
    @AfterClass
    public void tearDown() {
    	//driver.close();
        pageObjectManager.resetPageObjects();
    }

}
