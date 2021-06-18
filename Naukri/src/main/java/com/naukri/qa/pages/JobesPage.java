package com.naukri.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naukri.qa.base.BaseComn;

public class JobesPage extends BaseComn {

	// Page Factory - OR:
	@FindBy(xpath = "//div[text()='My Naukri']")
	// @CacheLookup
	WebElement myNaukriLink;

	@FindBy(xpath = "//a[text()='Edit Profile']")
	// @CacheLookup
	WebElement editProfileLink;

	@FindBy(xpath = "//em[text()='Edit']")
	// @CacheLookup
	WebElement editpencil;

	@FindBy(xpath = "//input[@id='name']")
	// @CacheLookup
	WebElement updateName;

	@FindBy(xpath = "//input[@id='mob_number']")
	// @CacheLookup
	WebElement updateMobileNumber;

	@FindBy(xpath = "//button[@id='saveBasicDetailsBtn']")
	// @CacheLookup
	WebElement saveBtn;

	@FindBy(xpath = "//input[@id='attachCV']")
	// @CacheLookup
	WebElement uploadResumeLink;

	// Initializing Page Factory:
	public JobesPage(WebDriver jdriver) {
		driver = jdriver;
		PageFactory.initElements(driver, this);
	}

	// Action Methods:
	public boolean validateMyNaukriLink() {
		return myNaukriLink.isDisplayed();
	}

	public JobesPage clickOnEditProfileLink(String aUpdateName, String aUpdateMobileNumber) {
		Actions act = new Actions(driver);
		try {
			act.moveToElement(myNaukriLink).build().perform();
			Thread.sleep(5000);
			editProfileLink.click();
			editpencil.click();
			updateName.clear();
			updateName.sendKeys(aUpdateName);
			Thread.sleep(2000);
			updateMobileNumber.clear();
			updateMobileNumber.sendKeys(aUpdateMobileNumber);
			Thread.sleep(2000);
			saveBtn.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new JobesPage(driver);
	}

	public JobesPage clickOnUpdateResume() {
		Actions act = new Actions(driver);
		try {
			act.moveToElement(myNaukriLink).build().perform();
			Thread.sleep(5000);
			editProfileLink.click();
			uploadResumeLink.sendKeys(
					"C:\\Users\\HP\\Desktop\\Basavaraj Halli_QA-Automation_1.1yr Exp_Immidiately Available.pdf");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new JobesPage(driver);
	}

	public void closeBrowser() {
		driver.quit();
	}

}
