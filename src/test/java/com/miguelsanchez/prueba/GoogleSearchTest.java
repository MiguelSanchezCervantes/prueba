package com.miguelsanchez.prueba;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTest {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
	}
	
	
	@Test
	public void testGooglePage() throws InterruptedException {
		WebElement searchbox = driver.findElement(By.name("q"));
		searchbox.clear();
		searchbox.sendKeys("Oracle");
		searchbox.submit();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("rc"), 1));
		List<WebElement> results = driver.findElements(By.className("rc"));
		WebElement webpage = results.get(0).findElement(By.xpath("./div/a"));
		webpage.click();
		wait.until(ExpectedConditions.urlToBe("https://www.oracle.com/index.html"));
		System.out.println(driver.getCurrentUrl());
	}
	
	@After
	public void closeWindow() {
		driver.quit();
	}
}
