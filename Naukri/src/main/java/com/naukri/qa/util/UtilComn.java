package com.naukri.qa.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UtilComn {
	public static int PAGE_LOAD_TIME = 35;
	public static int IMPLICIT_WAIT_TIME = 10;
	static String parent;
	private WebDriver driver;

	public UtilComn(WebDriver driver) {
		this.driver = driver;
	}

	// Close the Pop-Up windows
	public WebDriver closeWindowPopUps() {
		Set<String> wndws = driver.getWindowHandles();
		int windowNos = wndws.size();
		Iterator<String> itr = wndws.iterator();
		if (windowNos >= 2) {
			parent = itr.next();
			while (itr.hasNext() == true) {
				String s = itr.next();
				driver.switchTo().window(s).close();
			}
			driver.switchTo().window(parent);
		}
		return driver;
	}

	// Close the permission pop-Up requests for LOGIN-PAGE
	public WebDriver closeLoginPgPopUps() {
		driver.findElement(By.xpath("//button[text()='GOT IT']")).click();
		// driver.findElement(By.id("block")).click();
		return driver;
	}

	// Check for Invalid links
	public void checkForInvalidLinks() throws IOException {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total No of links in Login page : " + links.size());
		for (WebElement ele : links) {
			System.out.println(links.indexOf(ele) + ".) " + ele.getAttribute("href"));
		}
		int brokenLink = 0;
		for (WebElement ele : links) {

			// System.setProperty("http.maxRedirects", "999");
			String link = ele.getAttribute("href");
			if (link == null || link.isEmpty()) {
				System.out.println(links.indexOf(ele) + ".) " + "URL is Empty");
				continue;
			} else if (link != "javascript:void(0)" || link != "javascript:;") {
				URL url = new URL(link);
				try {
					HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
					httpcon.connect();
					if (httpcon.getResponseCode() >= 400) {
						System.out.println((links.indexOf(ele) + ".) " + httpcon.getResponseCode() + " : " + link
								+ "  Invalid link"));
						brokenLink++;
					} else {
						System.out
								.println(links.indexOf(ele) + ".) " + httpcon.getResponseCode() + link + " valid Link");
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

		}
		System.out.println("The Number of Broken links are : " + brokenLink);

	}

}
