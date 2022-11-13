package uitests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class SmokeTest extends BaseTest {

	/** The Log. */
	@SuppressWarnings("unused")
	private static Logger Log = LogManager.getLogger(SmokeTest.class.getName());

	/**
	 * Test Login Flow.
	 * Verify home page after login
	 */
	@Test(description = "Verify Home Page of Amazon application")
	public void verifyAmazonHomepage_Test() 
	{
		test = extent.startTest("Verify Amazon application Home Page");
		test.log(LogStatus.INFO,"Open the Amazon Url");
		test.log(LogStatus.INFO, "Verify Home page");
		pages.gethomePage().openAmazonApp();
		test.log(LogStatus.INFO, "Verify Sign-in button on Home page");
		pages.gethomePage().verifySignInButton();
		test.log(LogStatus.PASS, "Verify Amazon application Home Page");
	}
	
	@Test(description = "Verify the Footer Social links on Home page")
	public void verifyFooterSocialLinks_Test() throws Exception
	{
		test = extent.startTest("Verify the footer social links on Amazon application's Home Page");
		test.log(LogStatus.INFO,"Open the Amazon Url");
		pages.gethomePage().openAmazonApp();
		test.log(LogStatus.INFO, "Verify Social links present on Home page");
		pages.gethomePage().verifyFooterSocialLinks();
		test.log(LogStatus.PASS, "Verify the footer social links on Amazon application's Home Page");
	}
	
	@Test(description = "Verify the Sign-in Page")
	public void verifySigninPage_Test()
	{
		test = extent.startTest("Verify Sign- in page of Amazon application");
		test.log(LogStatus.INFO,"Open the Amazon Url");
		pages.gethomePage().openAmazonApp();
		test.log(LogStatus.INFO, "Verify Sign-in button on Home page");
		pages.gethomePage().verifySignInButton();
		test.log(LogStatus.INFO,"Verify Sign-in Page");
		pages.gethomePage().verifySignInPage();
		test.log(LogStatus.PASS, "Verify Sign- in page of Amazon application");
	}
	
	@Test
	public void verifyCreateAmazonAccountPage_Test()
	{
		test = extent.startTest("Verify Create Amazon account page of Amazon application");
		test.log(LogStatus.INFO,"Open the Amazon Url");
		pages.gethomePage().openAmazonApp();
		test.log(LogStatus.INFO, "Verify Create account page");
		pages.gethomePage().verifyCreateAmazonAccountPage();
		test.log(LogStatus.PASS, "Verify Sign- in page of Amazon application");
	}
	
	@Test
	public void verifyIncorrectMobileNumberOnSignIN_Test()
	{
		test = extent.startTest("Verify Error message on Entering Incorrect mobile number on sign-in");
		test.log(LogStatus.INFO,"Open the Amazon Url");
		pages.gethomePage().openAmazonApp();
		test.log(LogStatus.INFO, "Entering incorrect mobile number on sign-in page");
		pages.gethomePage().verifyIncorrectMobileNumberOnSignin();
		test.log(LogStatus.PASS, "Verify Error message on Entering Incorrect mobile number on sign-in");
	}
	
	@Test(description = "Verify the Hamburger Menu")
	public void verifyHamburgerMenu_Test()
	{
		test = extent.startTest("Verify Hamburger menu on Home Page of Amazon application");
		test.log(LogStatus.INFO,"Open the Amazon Url");
		pages.gethomePage().openAmazonApp();
		test.log(LogStatus.INFO, "Verify Sign-in button on Home page");
		pages.gethomePage().verifySignInButton();
		test.log(LogStatus.INFO,"Verify Hamburger Menu");
		pages.gethomePage().verifyHamburgerMenu();	
		test.log(LogStatus.PASS, "Verify Hamburger menu on Home Page of Amazon application");
	}
	
	@Test(description = "Verify the Your account page")
	public void verifyYourAccountPage_Test()
	{
		test = extent.startTest("Verify Your Account Page of Amazon application");
		test.log(LogStatus.INFO,"Open the Amazon Url");
		pages.gethomePage().openAmazonApp();
		test.log(LogStatus.INFO,"Open the Your account page");
		pages.gethomePage().verifyYourAccountPage();
		test.log(LogStatus.PASS, "Verify Your Account Page of Amazon application");
	}
	
	@Test(dataProviderClass = BaseTest.class, dataProvider = "SearchData")
	public void searchMobilePhones(String data)
	{
		test = extent.startTest("Search Mobile phones and verify search results on Amazon application");
		test.log(LogStatus.INFO,"Open the Amazon Url");
		pages.gethomePage().openAmazonApp();
		test.log(LogStatus.INFO,"Searching for "+data+" mobiles phones");
		pages.gethomePage().searchMobile(data);
		test.log(LogStatus.PASS, "Search Mobile phones and verify search results on Amazon application");
	}
	
	
}
