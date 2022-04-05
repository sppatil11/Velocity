package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilityClasses.Util1;

public class HomePage extends Util1 {

	WebDriver driver;
	@FindBy(xpath = "//*[@class='exehdJ']")
	private WebElement nameCheck;

	@FindBy(xpath = "(//div[@class='_28p97w'])[1]")
	private WebElement profileNameTxt;

	@FindBy(xpath = "//div[text()='My Profile']")
	private WebElement myProfileTxt;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;

	}

	public String isPageLoaded() {
		WebElement element = explicitWait(driver, nameCheck, 10);
		String text = element.getText();
		return text;
	}

	public void hoverToProileName() {
		moveToElement(driver, profileNameTxt);
	}

	public void clickOnMyProfile() {
		myProfileTxt.click();
	}

	public void movePointer() throws InterruptedException {
		moveByOffset(driver);
		Thread.sleep(1000);

	}
}
