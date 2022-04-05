package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityClasses.Util1;

public class LogInPage extends Util1 {

	WebDriver driver;
	@FindBy(xpath = "//*[@class=\"_2IX_2- VJZDxU\"]")
	private WebElement emailID;

	@FindBy(xpath = "//*[@type=\"password\"]")
	private WebElement password;

	@FindBy(xpath = "(//*[@type=\"submit\"])[2]")
	private WebElement submitButton;

	public LogInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void enterEmailID() {
		emailID.sendKeys("Shashank.patil.11@gmail.com");
//		Reporter.log("Entered email ID", true);
	}

	public void enterPassword() {
		password.sendKeys("Shashank@11");
//		Reporter.log("Entered password", true);
	}

	public void clickSubmitButton() {
		submitButton.click();
//		Reporter.log("click on submit button", true);
	}

}
