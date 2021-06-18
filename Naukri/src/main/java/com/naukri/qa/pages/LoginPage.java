/*
 * Locate all the LoginPage WebElements
 * Initialize the WebElements
 * 
 * */
package com.naukri.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naukri.qa.base.BaseComn;

public class LoginPage extends BaseComn {

	// Page-Factory -- Object Repository:
	@FindBy(xpath = "//div[contains(text(),'Login')]")
	@CacheLookup
	WebElement loginLink;

	@FindBy(xpath = "//input[@type='text']")
	@CacheLookup
	WebElement username;

	@FindBy(xpath = "//input[@type='password']")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	WebElement loginBtn;

	// Initializing page Objects:
	public LoginPage(WebDriver ldriver) {
		driver = ldriver;
		PageFactory.initElements(driver, this);
	}

	// Action methods:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateLoginLink() {
		return loginLink.isDisplayed();
	}

	public JobesPage Login(String un, String pwd) throws InterruptedException {
		loginLink.click();
		Thread.sleep(1000);
		username.clear();
		username.sendKeys(un);
		Thread.sleep(1000);
		password.clear();
		password.sendKeys(pwd);
		Thread.sleep(1000);
		loginBtn.click();
		return new JobesPage(driver);
	}

	public void CloseBrowser() {
		driver.quit();
	}

	/*
	 * public ArrayList checkForBrokenLink() throws IOException { List<WebElement>
	 * links = driver.findElements(By.tagName("a")); Iterator<WebElement> itr =
	 * links.iterator(); ArrayList<String> list = new ArrayList<String>(); int
	 * invalidLink = 0; System.out.println("Invalid Links from Login Page are: ");
	 * while (itr.hasNext() == true) { WebElement linkEle = itr.next(); String alink
	 * = linkEle.getAttribute("href"); URL url = new URL(alink); HttpURLConnection
	 * con = (HttpURLConnection) url.openConnection(); con.connect(); if
	 * (con.getResponseCode() >= 400) { list.add(alink); invalidLink++; } }
	 * System.out.println("No of Invalid Links from the Login page are: " +
	 * invalidLink); return list; }
	 */

}
