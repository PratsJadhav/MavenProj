package MavenProj.uiActions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import MavenProj.testBase.TestBase;

public class HomePage extends TestBase {
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;

	@FindBy(xpath = ".//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	WebElement signIn;
	
	@FindBy(xpath = ".//*[@id='email']")
	WebElement usernameTxtBox;
	
	@FindBy(xpath = ".//*[@id='passwd']")
	WebElement passwordTxtBox;
	
	@FindBy(xpath = ".//*[@id='SubmitLogin']")
	WebElement loginBtn;
	
	@FindBy(xpath = ".//*[@id='center_column']/div[1]/ol/li")
	WebElement authonticationFailed;
	
	@FindBy(id = "email_create")
	WebElement emailAddressTxt;
	
	@FindBy(id = "SubmitCreate")
	WebElement createAccountBtn;
	
	@FindBy(xpath = ".//*[@id='id_gender2']")
	WebElement prefix;
	
	@FindBy(xpath = ".//*[@id='customer_firstname']")
	WebElement fname;
	
	@FindBy(xpath = ".//*[@id='customer_lastname']")
	WebElement lname;
	
	@FindBy(xpath = ".//*[@id='passwd']")
	WebElement password;
	
	@FindBy(xpath = ".//*[@id='firstname']")
	WebElement firstName;
	
	@FindBy(xpath = ".//*[@id='lastname']")
	WebElement lastName;
	
	@FindBy(xpath = ".//*[@id='address1']")
	WebElement address;
	
	@FindBy(xpath =  ".//*[@id='city']")
	WebElement city;
	
	@FindBy(xpath = ".//*[@id='id_state']")
	WebElement stateDropdown;
	
	@FindBy(xpath= ".//*[@id='postcode']")
	WebElement zipCode;
	
	@FindBy(xpath = ".//*[@id='id_country']")
	WebElement countryDropdown;
	
	@FindBy(xpath = ".//*[@id='phone_mobile']")
	WebElement phone;
	
	@FindBy(xpath = ".//*[@id='submitAccount']")
	WebElement registerBtn;
	
	@FindBy(xpath = ".//*[@id='header']/div[2]/div/div/nav/div[2]/a[@title='Log me out']")
	WebElement logout;
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void loginToApplication(String username, String password)
	{
		signIn.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		log.info("Clicking on sign-in button");
		log.info("Entered Username :"+username);
		usernameTxtBox.sendKeys(username);
		log.info("Entered Password :"+password);
		passwordTxtBox.sendKeys(password);
		log.info("Clicking on login button");
		loginBtn.click();
		
	}
	
	public Boolean verifyLogOutDisplay()
	{
		try
		{
			logout.isDisplayed();
			log.info("log out is displayed....");
	      return true;
		}catch(Exception e)
		{
			log.info("log out is not displayed");
			return false;
		}
	}
	
	public void logOut()
	{
		log.info("Clicking on log out link");
		logout.click();
		
	}
	public void createAccount(String emailAddress)
	{
		log.info("clicking on sign in button...");
		signIn.click();
		
		log.info("enetering email address...");
		emailAddressTxt.sendKeys(emailAddress);
		log.info("clicking on create account button...");
		createAccountBtn.click();
		
	}
	
	public void personalInformation(String fname, String lname, String password, String firstName, String lastName, 
			String address, String city, String zipCode, String phone)
	{
		prefix.click();
		this.fname.sendKeys(fname);
		this.lname.sendKeys(lname);
		this.password.sendKeys(password);
		this.firstName.sendKeys(firstName);
		this.lastName.sendKeys(lastName);
		this.address.sendKeys(address);
		this.city.sendKeys(city);
		Select list = new Select(stateDropdown);
		list.selectByIndex(3);
		this.zipCode.sendKeys(zipCode);
		Select list1 = new Select(countryDropdown);
		list1.selectByValue("21");
		this.phone.sendKeys(phone);
		registerBtn.click();
	}
	
	
}
