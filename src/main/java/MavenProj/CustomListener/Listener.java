package MavenProj.CustomListener;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import MavenProj.CustomListener.Listener;
import MavenProj.excelReader.ExcelReader;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


import MavenProj.testBase.TestBase;

public class Listener extends TestBase implements ITestListener{

		
	
	public void onFinish(ITestContext arg0) {
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    	
    	String methodName = result.getName();
    	
    	if(result.isSuccess())
    	{
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		
    		try
    		{
    			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\MavenProj\\screenshots\\";
            	File destFile = new File((String)reportDirectory +formater.format(calendar.getTime())+".png");
            	FileUtils.copyFile(scrFile, destFile);
                Reporter.log(" <a href = '" + destFile.getAbsolutePath() + "'> <img src = '" +destFile.getAbsolutePath() + " ' height ='100' width = '100' / > </a>");
            }catch(Exception e)
            {
            	e.printStackTrace();
            }
    		
    	}
		
		
	}
	
	public void onTestFailure(ITestResult result) {
	
	 // TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    	String methodName = result.getName();
    	
    	if(!result.isSuccess())
    	{
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		
    		try
    		{
    			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\MavenProj\\screenshots\\";
            	File destFile = new File((String)reportDirectory +formater.format(calendar.getTime())+".png");
            	FileUtils.copyFile(scrFile, destFile);
                Reporter.log(" <a href = '" + destFile.getAbsolutePath() + "'> <img src = '" +destFile.getAbsolutePath() + " ' height ='100' width = '100' / > </a>");
            }catch(Exception e)
            {
            	e.printStackTrace();
            }
    		
    	}
    	
        
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		
		
	}
	

	


public void onTestSuccessPractice(ITestResult result) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    	
    	String methodName = result.getName();
    	
    	if(result.isSuccess())
    	{
    		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    		
    		try
    		{
    			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\MavenProj\\screenshots\\";
            	File destFile = new File((String)reportDirectory +formater.format(calendar.getTime())+".png");
            	FileUtils.copyFile(scrFile, destFile);
                Reporter.log(" <a href = '" + destFile.getAbsolutePath() + "'> <img src = '" +destFile.getAbsolutePath() + " ' height ='100' width = '100' / > </a>");
            }catch(Exception e)
            {
            	e.printStackTrace();
            }
    		
    	}
		
}
}