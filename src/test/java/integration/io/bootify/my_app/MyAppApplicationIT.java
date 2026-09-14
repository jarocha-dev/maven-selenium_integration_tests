package io.bootify.my_app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class MyAppApplicationIT {
	/*
	 * My App running at localhost:8080
	 * Title: Welcome to your new app! - My App
	 * 
	 * Controllers:
	 *  - /bikeModels
	 *  - /userBikes
	 *  - /maintenanceLogs
	 */
	
	private static WebDriver driver = null;
	private static String testOutputFolder = null; 
	
	@BeforeAll
	public static void setupBeforeClass() {
		testOutputFolder = "target/failsafe-reports/" + Thread.currentThread().getStackTrace()[1].getClassName().replace('.', '/') + "/";
		final FirefoxOptions firefoxOptions = new FirefoxOptions();
		
		firefoxOptions.addArguments(
				"-headless"
				/*"-profile-root", "/home/sandbox/git/bootify_sample_app/firefox_profile"*/);
		
		driver = new FirefoxDriver(firefoxOptions);
	}
	
	@Test
	void testGetIndex_GetBikeModels() throws IOException {
		final String testName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
        driver.get("http://localhost:8080");

        assertEquals("Welcome to your new app! - My App", driver.getTitle());

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        takeScreenshotTo(driver, testOutputFolder + testName + "_index.png");
        
        WebElement textBox = driver.findElement(By.linkText("Bike Models"));
//	        WebElement submitButton = driver.findElement(By.cssSelector("button"));

//	        textBox.sendKeys("Selenium");
//	        submitButton.click();
        textBox.click();
        
        takeScreenshotTo(driver, testOutputFolder + testName + "_bikeModels.png");

//	        WebElement message = driver.findElement(By.id("message"));
//	        message.getText();
        assertEquals("Bike Models - My App", driver.getTitle());
	}
	
	@Test
	void testGetIndex_GetUserBikes() throws IOException {
		final String testName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
        driver.get("http://localhost:8080");

        assertEquals("Welcome to your new app! - My App", driver.getTitle());

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.findElement(By.linkText("User Bikes")).click();

        takeScreenshotTo(driver, testOutputFolder + testName + "_userBikes.png");

        assertEquals("User Bikes - My App", driver.getTitle());
	}

	private void takeScreenshotTo(final WebDriver driver, final String filepath) throws IOException {
		org.apache.commons.io.FileUtils.copyFile(
				((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
				new File(filepath));
	}
	
	@AfterAll
	public static void tearDownAfterClass() {
		driver.quit();
	}
}
