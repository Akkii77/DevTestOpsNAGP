package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class PageCollection 
{
	/** The Log. */
	private static Logger Log = LogManager.getLogger(PageCollection.class.getName());

	/** The driver. */
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/** The login page. */
	private HomePage homePage;
			
	/**
	 * Instantiates a new page collection.
	 *
	 * @param driver the driver
	 */
	public PageCollection(ThreadLocal<WebDriver> driver) 
	{
		this.driver = driver;
		Log.info("Initialized Page Collection Class");
	}

	public HomePage gethomePage() 
	{
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}
	
}