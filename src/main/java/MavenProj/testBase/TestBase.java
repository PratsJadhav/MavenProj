package MavenProj.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import MavenProj.CustomListener.Listener;
import MavenProj.excelReader.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public static ExtentReports extent;
    public static ExtentTest test;
    public static Properties CONFIG = null;
	public String browser = "Chrome";
    
    public ExcelReader excel;
    public Listener lis;
    public File fs;
    public FileInputStream fileinputstream;
    
    
    
    static 
    {
    	Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		extent = new ExtentReports(System.getProperty("user.dir") + "\\Reports\\test_" + formater.format(calendar.getTime()) + ".html", false);
		
    }
    
    public void getresult(ITestResult result)
    {
    	if(result.getStatus() == ITestResult.SUCCESS )
    	{
    		test.log(LogStatus.PASS, result.getName()+" test is pass");
    	}
    	else if(result.getStatus() == ITestResult.SKIP)
    	{
    		test.log(LogStatus.SKIP, result.getName()+" test is skipped and skip reason is: " + result.getThrowable());
    	}
    	else if(result.getStatus() == ITestResult.FAILURE)
    	{
    		test.log(LogStatus.ERROR, result.getName()+ "test is failed" + result.getThrowable());
    	}
    	else if(result.getStatus() == ITestResult.STARTED)
    	{
    		test.log(LogStatus.INFO, result.getName() + "test is started");
    	}
    }
    
    @AfterMethod()
    public void afterMethod(ITestResult result){
    	getresult(result);
    }
    
    @BeforeMethod()
    public void beforeMethod(Method result)
    {
    	test = extent.startTest(result.getName());
    	test.log(LogStatus.INFO, result.getName() + "test started");
    }
    
    @AfterClass(alwaysRun = true)
    public void testEnd()
    {
    	extent.endTest(test);
    	extent.flush();
    }
    
    public void loadPropertiesFile() throws IOException
    {
    	CONFIG = new Properties();
    	fileinputstream  = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\MavenProj\\Config\\Config.properties");
    	CONFIG.load(fileinputstream);
      	
     }
   
    public void init() throws IOException
    {
    	loadPropertiesFile();
    	selectBrowser(browser);
    	System.out.println(""+CONFIG.getProperty("url"));
    	getURL(CONFIG.getProperty("url"));
    	String log4jConfigPath = "log4j.Properties";
    	PropertyConfigurator.configure(log4jConfigPath);
    }
    
    
    public void selectBrowser(String browser)
    {
    	if(browser.equalsIgnoreCase("Chrome"))
    	{
    		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
    		driver = new ChromeDriver();
    	}
    }
    
    public void getURL(String url)
    {
    	driver.get(url);
    	log.info("opening browser");
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    public String[][] getData(String excelName, String sheetName) throws IOException
    {
    	String path = System.getProperty("user.dir")+ "\\src\\main\\java\\MavenProj\\data\\"+excelName;
    	excel = new ExcelReader(path);
    	String [][] data = excel.getDataFromSheet(sheetName,excelName);
    	return data;
    }
    
    public void waitForElement(int timeOutInSeconds, WebElement element)
    {
    	WebDriverWait	wait = new WebDriverWait(driver, timeOutInSeconds);
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void getScreenshot(String name)
    {
    	Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    	
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    
        try
        {
        	String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\MavenProj\\screenshots\\";
        	File destFile = new File((String)reportDirectory + name + "_" +formater.format(calendar.getTime())+".png");
        	FileUtils.copyFile(scrFile, destFile);
        	
        	Reporter.log(" <a href = '" + destFile.getAbsolutePath() + "'> <img src = '" +destFile.getAbsolutePath() + " ' height ='100' width = '100' / > </a>");
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
    
}
