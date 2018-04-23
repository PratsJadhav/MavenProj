package MavenProj.homePage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MavenProj.testBase.TestBase;
import MavenProj.uiActions.HomePage;

public class TC_newUser extends TestBase{

	HomePage homepage;
	public static final Logger log = Logger.getLogger(TC_newUser.class.getName());
	
	@DataProvider(name = "loginData")
	public String[][] getTestData() throws IOException
	{
		String[][] testRecords = getData("DataExcel.xlsx","Sheet1" );
		return testRecords;
	}
	
	
	@BeforeTest
	public void setup() throws InterruptedException, IOException
	{
	init();
	}
	
	@Test(dataProvider = "loginData", priority = 1)
	public void userLogin(String email, String pass, String runMode)
	{
		if(runMode.equalsIgnoreCase("n"))
		{
			throw new SkipException("Skipping as Run mode set as No");
		}
		else
		{
			
		log.info("***************** Login with different credentials ************************");
		homepage = new HomePage(driver);
		
		homepage.loginToApplication(email, pass);
	
		Boolean status = homepage.verifyLogOutDisplay();
		if(status)
		{
			log.info("Successfull login");
			getScreenshot("testLogin_"+email);
			homepage.logOut();
		}
		else{
			log.info("Error while login as log out link is not displayed");
			
		}
		}
	}
	
	@Test(priority = 2)
	public void newUserRegistration()
	{
		homepage = new HomePage(driver);
		log.info("***************** Create new user ************************");
		homepage.createAccount("amoljadhao1@gmail.com");
		homepage.personalInformation("amolo1", "jadhavo1", "P@ssw0rd", "amol", "jadhav","address", "city", "12332", "8806468985");
	}
	
}
