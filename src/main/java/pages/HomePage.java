/*
 *
 */
package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BasePage;
import utils.TestUtils;

public class HomePage extends BasePage {
	
	

	/** The Log. */
	private static Logger Log = LogManager.getLogger(HomePage.class.getName());

	private By HomePageIcon = By.cssSelector("[aria-label='Amazon']");
	private By SigninBtn = By.cssSelector("[data-nav-ref='nav_ya_signin']");
	private By SigninHeading = By.xpath("//h1[contains(text(),'Sign-In')]");
	private By InputEmailField = By.cssSelector("#ap_email");
	private By SubmitBtn = By.cssSelector("input#continue");
	private By HamburgerMenu = By.cssSelector("#nav-hamburger-menu");
	private By TrendingMenuItem = By.xpath("//div[text()='trending']");
	private By DigitalContentMenu = By.xpath("//div[text()='digital content and devices']");
	private By ShopByDepartmentMenu = By.xpath("//div[text()='shop by department']");
	private By ProgramsAndFeatureMenu = By.xpath("//div[text()='programs & features']");
	private By HelpAndSettingsMenu = By.xpath("//div[text()='help & settings']"); 
	private By YourAccountBtn = By.xpath("//a[@class='hmenu-item' and text()='Your Account']");
	private By YourAccountHeading = By.xpath("//h1[contains(text(),'Your Account')]");
	private By YourOrderlink = By.xpath("//h2[contains(text(),'Your Orders')]");
	private By LoginAndSecuritylink = By.xpath("//h2[contains(text(),'Login & security')]");
	private By PrimeAccountlink = By.xpath("//h2[contains(text(),'Prime')]");
	private By YourAddresseslink = By.xpath("//h2[contains(text(),'Your Addresses')]");
	private By PaymentOptionslink = By.xpath("//h2[contains(text(),'Payment options')]");
	private By AmazonPayBalancelink = By.xpath("//h2[contains(text(),'Amazon Pay balance')]");
	private By SearchTextBox = By.cssSelector("#twotabsearchtextbox");
	private By SubmitSearchBtn = By.cssSelector("#nav-search-submit-button");
	private By FirstSearchItemResult = By.xpath("(//div[@class='a-section']//h2//span)[1]");
	private By CreateAmazonAccountBtn = By.cssSelector("#createAccountSubmit");
	private By CreateAccountHeading = By.xpath("//h1[contains(text(),'Create Account')]");
	private By NameInputField = By.cssSelector("#ap_customer_name");
	private By PhoneNumberInputField = By.cssSelector("#ap_phone_number");
	private By EmailInputField = By.cssSelector("#ap_email");
	private By PasswordInputField = By.cssSelector("#ap_password");
	private By ContinueBtn = By.cssSelector("#continue");
	private By ErrorMessage = By.cssSelector("#auth-error-message-box h4");
	private By FacebookLink = By.xpath("//a[text()='Facebook']");
	private By TwitterLink = By.xpath("//a[text()='Twitter']");
	private By InstagramLink = By.xpath("//a[text()='Instagram']");
	
	/**
	 * 
	 * @param driver the driver
	 */
	// initialize elements
	public HomePage(ThreadLocal<WebDriver> driver) {

		super(driver);
		this.driver = driver;
		Log.info("Initializing login Page Objects");
		PageFactory.initElements(driver.get(), HomePageIcon);
	}


	public void openAmazonApp()  
	{
		gotoURL(TestUtils.getProperty("url"));
		waitForElementToBecomeVisible(HomePageIcon, longWait);
		Log.info("Amazon Home Page is loaded Successfully");

	}
	
	public void verifySignInButton()
	{
		waitForElementToBeClickable(SigninBtn, longWait);
		Assert.assertTrue(isElementPresent(SigninBtn), "The Sign-in button is not present on the Home Page");
		Log.info("Sign-in button is verified Successfully");
	}
	
	public void verifySignInPage()  
	{
		Click(SigninBtn);
		waitForElementToBecomeVisible(SigninHeading, longWait);
		Assert.assertTrue(isElementPresent(SigninHeading), "Sign-in Heading is not present on the Sign-in page");
		Assert.assertTrue(isElementPresent(InputEmailField), "Input for Email field is not present on the  Sign-in Page");
		Assert.assertTrue(isElementPresent(SubmitBtn), "Continue button is not present on the  Sign-in Page");
		Log.info("Sign-in page is verified Successfully");

	}
	
	public void verifyCreateAmazonAccountPage()
	{
		Click(SigninBtn);
		waitForElementToBeClickable(CreateAmazonAccountBtn, longWait);
		clickAndWait(CreateAmazonAccountBtn, longWait);
		Assert.assertTrue(isElementPresent(CreateAccountHeading), "Create Account Heading is not present on the create amazon account page");
		Assert.assertTrue(isElementPresent(NameInputField), "Name Input Field is not present on the create amazon account page");
		Assert.assertTrue(isElementPresent(PhoneNumberInputField), "Phone number input field is not present on the create amazon account page");
		Assert.assertTrue(isElementPresent(EmailInputField), "Email input field is not present on the create amazon account page");
		Assert.assertTrue(isElementPresent(PasswordInputField), "Password input field is not present on the create amazon account page");
		Assert.assertTrue(isElementPresent(ContinueBtn), "Continue button is not present on the create amazon account page");	
		
	}
	
	public void verifyHamburgerMenu()
	{
		waitForElementToBeClickable(HamburgerMenu, longWait);
		Click(HamburgerMenu);
		waitForPageLoaded();
		waitForElementToBecomeVisible(TrendingMenuItem, longWait);
		Assert.assertTrue(isElementPresent(TrendingMenuItem), "Trending menu item is not present on hamburger menu");
		Assert.assertTrue(isElementPresent(DigitalContentMenu), "Digital content menu item is not present on hamburger menu");
		Assert.assertTrue(isElementPresent(ShopByDepartmentMenu), "Shop by department menu item is not present on hamburger menu");
		Assert.assertTrue(isElementPresent(ProgramsAndFeatureMenu), "Programs and features menu item is not present on hamburger menu");
		Assert.assertTrue(isElementPresent(HelpAndSettingsMenu), "Help and settings menu item is not present on hamburger menu");
		Log.info("Hamburger menu is verified Successfully");
		
	}
	
	public void verifyYourAccountPage()
	{
		waitForElementToBeClickable(HamburgerMenu, longWait);
		Click(HamburgerMenu);
		waitForElementToBeClickable(YourAccountBtn, longWait);
		clickAndWait(YourAccountBtn, longWait);
		Assert.assertTrue(isElementPresent(YourAccountHeading), "Your Account page is not being loaded properly");
		Log.info("Your Account page is loaded Successfully");
		Assert.assertTrue(isElementPresent(YourOrderlink), "Your Order link is not present in your account page");
		Log.info("Your Order link is verified Successfully");
		Assert.assertTrue(isElementPresent(LoginAndSecuritylink), "Login and security link is not present in your account page");
		Assert.assertTrue(isElementPresent(PrimeAccountlink), "Prime account link is not present in your account page");
		Assert.assertTrue(isElementPresent(YourAddresseslink), "Your Addresses link is not present in your account page");
		Assert.assertTrue(isElementPresent(PaymentOptionslink), "Payment Options link is not present in your account page");
		Assert.assertTrue(isElementPresent(AmazonPayBalancelink), "Amazon pay balance link is not present in your account page");
		Log.info("Your Account page is verified Successfully");
	}
	
	public void searchMobile(String data)
	{
		System.out.println(data);
		waitForElementToBeClickable(SearchTextBox, longWait);
		setText(SearchTextBox, data, longWait);
		clickAndWait(SubmitSearchBtn, longWait);
		String Actual = getText(FirstSearchItemResult).toLowerCase();
		String Expected = data.toLowerCase();
		Assert.assertTrue(Actual.contains(Expected),"Searched Item is not present in search results");
		Log.info("Search functionality is verified Successfully");
	}
	
	public void verifyIncorrectMobileNumberOnSignin()
	{
		String Mobile = TestUtils.getProperty("mobile");
		
		Click(SigninBtn);
		waitForElementToBecomeVisible(InputEmailField, longWait);
		setText(InputEmailField, Mobile, longWait);
		clickAndWait(SubmitBtn, longWait);
		String Actual = TestUtils.getProperty("Error");
		String Expected = getText(ErrorMessage);
		Assert.assertTrue(Actual.equals(Expected), "The Error message is not present");
		Log.info("Incorrect mobile number is verified Successfully");
	}
	
	public void verifyFooterSocialLinks() throws Exception
	{
		 String facebooklink = getAttribute(FacebookLink, "href");
		 openNewTab1();
		 switchTab(1); 
		 driver.get().get(facebooklink); 
		 String Url = getCurrentUrl();
		 Assert.assertTrue(Url.contains("facebook")); 
		 switchTab(0);
		  
		 String twitterlink = getAttribute(TwitterLink, "href"); 
		 openNewTab1();
		 switchTab(2); 
		 driver.get().get(twitterlink); 
		 String Url1 = getCurrentUrl();
		 Assert.assertTrue(Url1.contains("twitter")); 
		 switchTab(0);
		 
		 String instagramlink = getAttribute(InstagramLink, "href"); 
		 openNewTab1();
		 switchTab(3); 
		 driver.get().get(instagramlink); 
		 String Url2 = getCurrentUrl();
		 Assert.assertTrue(Url2.contains("instagram")); 
		 
		 
		 
		
		
	}
	
}