package uitests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import actions.UIActions;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.PageCollection;
import utils.TestUtils;

public class BaseTest 
{
	/** The Log. */
	private static Logger Log = LogManager.getLogger(BaseTest.class.getName());
	
	protected static String downloadFolder = System.getProperty("user.dir") + "/" + "resources" + "/" + "downloadedFiles";


	/** The driver. */
	// Use the application driver
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/** The pages. */
	PageCollection pages;

	/** The web actions. */
	UIActions uiActions;

	/**
	 * Instantiates a new base test.
	 */

	public BaseTest() 
	{
		Log.info("Getting WebDriver Settings");
	}

	/**
	 * Creates the chrome driver.
	 */
	private void createChromeDriver() 
	{
		try {
			Log.info("Found Chrome as Browser");
			// set browser preferences to ignore browser notification pop up and to add
			// console errors
			
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("download.default_directory", downloadFolder);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
//			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
            options.addArguments("--whitelisted-ips");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-extensions");
			options.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized","use-fake-ui-for-media-stream"));
			String os = System.getProperty("os.name").toLowerCase();
			System.out.println("the Current OS is :"+os);
	
			Log.info("Launching Chrome and Maximizing it");
			driver.set(new ChromeDriver(options));

			driver.get().manage().window().maximize();

		   } catch (Exception e) 
		      {
			   Log.info("unable to create chrome driver");
			   e.printStackTrace();
		      }
	}
	
	public void createFirefoxDriver()
	{
		try {
			Log.info("Found Firefox as Browser");
			// set browser preferences to ignore browser notification pop up and to add
			// console errors
			
			WebDriverManager.firefoxdriver().setup();;
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("download.default_directory", downloadFolder);
			FirefoxOptions options = new FirefoxOptions();
//			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
            options.addArguments("--whitelisted-ips");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-extensions");
			String os = System.getProperty("os.name").toLowerCase();
			System.out.println("the Current OS is :"+os);
	
			Log.info("Launching Firefox and Maximizing it");
			driver.set(new FirefoxDriver(options));

			driver.get().manage().window().maximize();

		   } catch (Exception e) 
		      {
			   Log.info("unable to create firefox driver");
			   e.printStackTrace();
		      }
	}
	
	
	
	public void createEdgeDriver()
	{
		try {
			Log.info("Found Microsoft Edge as Browser");
			// set browser preferences to ignore browser notification pop up and to add
			// console errors
			
			WebDriverManager.edgedriver().setup();;
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("download.default_directory", downloadFolder);
			EdgeOptions options = new EdgeOptions();
//			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
            options.addArguments("--whitelisted-ips");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-extensions");
			String os = System.getProperty("os.name").toLowerCase();
			System.out.println("the Current OS is :"+os);
			
			Log.info("Launching Microsoft Edge and Maximizing it");
			driver.set(new EdgeDriver(options));

			driver.get().manage().window().maximize();

		   } catch (Exception e) 
		      {
			   Log.info("unable to create Microsoft Edge driver");
			   e.printStackTrace();
		      }
	}

	/**
	 * Driversetup.
	 */
	@BeforeMethod(alwaysRun = true)
	public void driversetup() {
		String browser = TestUtils.getProperty("selenium.browser");

		switch (browser) 
		{
		 case "chrome":
			createChromeDriver();
			break;
		 case "firefox":
			 createFirefoxDriver();
			 break;
		 case "Edge":
			 createEdgeDriver();
			 break;

		 default:
			Log.info("browser not supported");
			break;
		}
	}

	/**
	 * Initialize pages.
	 */
	@BeforeMethod(alwaysRun = true)
	public void initializePages() {
		pages = new PageCollection(driver);
		uiActions = new UIActions(driver);
	}

	/**
	 * Teardown.
	 *
	 * @param result the result
	 */
		public static ExtentReports extent;
		@SuppressWarnings("unused")
		private static final String BREAK_LINE = "</br>";
		public static ExtentTest test;

		@BeforeSuite
		public void before() {
			String path = null;
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh.mm a");
			File theDir = new File("target\\ExtentReports");
			if (!theDir.exists()){
			    theDir.mkdirs();
			}
			try {
				   path = 	Filepath();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String filename1 = path;
				File srcFile = new File(filename1);
				String DESTFILE = "target\\Archived test results\\ExtentReport"+df.format(new Date())+".html";
				File destFile = new File(DESTFILE);
				try {
					FileUtils.copyFile(srcFile, destFile);
					srcFile.delete();
					FileUtils.deleteDirectory(new File("target\\ExtentReports"));
				//	FileUtils.delete(srcFile);
				//	FileUtils.moveFile(srcFile, destFile);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
//				try {
//					srcFile.delete();
//					FileUtils.deleteDirectory(srcFile);
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
				
			extent = new ExtentReports("target\\ExtentReports\\Current test results"+df.format(new Date())+"\\ExtentReport"+df.format(new Date())+".html", true);
			
		}
		
		 
		
		
		@DataProvider(name = "SearchData")
		public static  Object[][] dataReader() throws IOException{
			
			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "/resources/ExcelData.xlsx"));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			XSSFRow row = sheet.getRow(0);
			
			Object[][] data = new Object[sheet.getLastRowNum()][row.getLastCellNum()];
			
			for(int i=0;i<sheet.getLastRowNum();i++) {
				
				row = sheet.getRow(i);
			
				for(int j=0;j<row.getLastCellNum();j++) {
				
				//System.out.println(row.getCell(j));
				data[i][j] = row.getCell(j).toString();
				
			    }
			}
			
			workbook.close();
			return data; 
		
		}
		

		@AfterMethod(alwaysRun = true)
		public void afterMainMethod(ITestResult result) throws Exception
		{
			if (result.getStatus() == ITestResult.FAILURE) 
			{
				captureScreenshot(result);
				String methodname= result.getMethod().getMethodName();
				String classname= getClass().getName();
				System.out.println("CLASSNME: "+classname);
				String summary=	"ERROR IN: "+classname+ ":: "+methodname+ ":: "+result.getThrowable().getMessage().split("\\r?\\n")[0];
				System.out.println(summary);
				test.log(LogStatus.FAIL, summary);
			//	test.log(LogStatus.FAIL, result.getThrowable().getMessage());
				extent.endTest(test);
				driver.get().quit();
			}
			
			else{	
				extent.endTest(test);
				driver.get().quit();				
			    }
		}	
		@AfterTest(alwaysRun = true)
		public void tearDown() {
			//reporter.endReport();
			//driver.quit();
			extent.flush();
			
		}
		
		public void captureScreenshot(ITestResult result) throws IOException, InterruptedException {
			try {
				String screenshotName = TestUtils.getFileName(result.getName());
				File screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
				String path = TestUtils.getPath();
				String screen = path + "/screenshots/" + screenshotName + ".png";
				File screenshotLocation = new File(screen);
				FileUtils.copyFile(screenshot, screenshotLocation);
				Thread.sleep(2000);
				String image= test.addScreenCapture(screen);
				test.log(LogStatus.FAIL, screenshotName, image);

				Reporter.log(
						"<a href= '" + screen + "'target='_blank' ><img src='" + screen + "'>" + screenshotName + "</a>");
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
		
		public String  Filepath() throws IOException {
			// TODO Auto-generated method stub
	     String FilepathAsStringmain ="";
	     Path dir = Paths.get(".//target//ExtentReports");  // specify your directory
			Optional<Path> lastFilePath = Files.list(dir)    // here we get the stream with full directory listing
//			    .filter(f -> !Files.isDirectory(f))  // exclude subdirectories from listing
			    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));  // finally get the last file using simple comparator by lastModified field

			if ( lastFilePath.isPresent() ) // your folder may be empty
			{
				String FilepathAsString = lastFilePath.toString().replace("[", "").replace("]", "").replace("Optional", "");
					
			   System.out.println(FilepathAsString);
			   Path dir1 = Paths.get(FilepathAsString);  // specify your directory

				Optional<Path> lastFilePath1 = Files.list(dir1)    // here we get the stream with full directory listing
				    .filter(f -> !Files.isDirectory(f))  // exclude subdirectories from listing
				    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));  // finally get the last file using simple comparator by lastModified field

				if ( lastFilePath1.isPresent() ) // your folder may be empty
				{
				  FilepathAsStringmain = lastFilePath1.toString().replace("[", "").replace("]", "").replace("Optional", "");
				   System.out.println(FilepathAsStringmain);
				   
				}     
			   
			} 
			return FilepathAsStringmain;
		}
	
}           

