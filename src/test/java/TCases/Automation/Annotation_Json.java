package TCases.Automation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.protobuf.Duration;

import junit.framework.Assert;
public class Annotation_Json {
	private RemoteWebDriver driver;

	@BeforeTest
	public void setup() throws MalformedURLException {
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		URL url = new URL("http://172.20.23.92:4444/wd/hub");
		driver = new RemoteWebDriver(url, dc);
	}
	  @Test(priority = 1)
	    public void login() throws InterruptedException {
	        driver.get("http://apollo2.humanbrain.in/");
	        driver.manage().window().maximize();
	        System.out.println("The server is opened successfully");

	        WebDriverWait wait = new WebDriverWait(driver, 50);
	        try {
	            WebElement viewerSectionLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/gallery.svg']")));
	            viewerSectionLink.click();
	            System.out.println("The Viewer Icon is clicked");

	            String parentWindow = driver.getWindowHandle();
	            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Log In ']")));
	            loginButton.click();
	            System.out.println("The login Button is clicked");

	            Set<String> allWindows = driver.getWindowHandles();
	            for (String window : allWindows) {
	                if (!window.equals(parentWindow)) {
	                    driver.switchTo().window(window);
	                    break;
	                }
	            }

	            WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']")));
	            emailInput.sendKeys("softwaretestingteam9@gmail.com");
	            System.out.println("Mail ID is entered");

	            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
	            nextButton.click();
	            System.out.println("The Next Button is clicked");

	            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
	            passwordInput.sendKeys("Health#123");
	            System.out.println("Password is entered");

	            WebElement nextButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
	            nextButton2.click();
	            System.out.println("The Next Button is clicked");

	            driver.switchTo().window(parentWindow);
	            Thread.sleep(5000);

	        } catch (NoSuchElementException e) {
	            System.err.println("Element not found: " + e.getMessage());
	        } catch (Exception e) {
	            System.err.println("An error occurred: " + e.getMessage());
	        }}
	  

	    @Test(priority = 2)
	    public void table() throws InterruptedException {
	        String parentWindow = driver.getWindowHandle();
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search tags']")));
	            searchInput.sendKeys("topo\n");
	            Thread.sleep(3000);
	            System.out.println("The number Entered Successfully");
	        } catch (Exception e) {
	            System.err.println("The number is not Entered successfully: " + e.getMessage());
	        }

	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            WebElement atlasEditorButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nb-icon[@nbtooltip='Atlas Editor']")));
	            atlasEditorButton.click();
	            Thread.sleep(3000);
	            System.out.println("The Atlas Editor is clicked");
	        } catch (Exception e) {
	            System.err.println("The Atlas Editor is not clicked: " + e.getMessage());
	        }
	        Thread.sleep(4000);
	        Set<String> allWindows = driver.getWindowHandles();
	        for (String window : allWindows) {
	            if (!window.equals(parentWindow)) {
	                driver.switchTo().window(window);
	                break;
	            }
	        }
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            WebElement annotation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nb-accordion-item-header[text()='Annotation']")));
	            annotation.click();
	            Thread.sleep(3000);
	            System.out.println("The Annotation icon is clicked");
	        } catch (Exception e) {
	            System.err.println("The Annotation icon is not clicked: " + e.getMessage());
	        }
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            WebElement h = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//i[@role='presentation'])[2]")));
	            h.click();
	            Thread.sleep(3000);
	            System.out.println("The Central Nervous system is clicked");
	        } catch (Exception e) {
	            System.err.println("The Central Nervous system is not clicked: " + e.getMessage());
	        }
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            WebElement g = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' 0 : Central nervous system-CNS ']")));

	            String actualText = g.getText().trim(); // Only trim spaces at start and end
	            String expectedText = "0 : Central nervous system-CNS"; // Expected text

	            // Debugging: Print actual and expected text
	            System.out.println("Actual Text: [" + actualText + "]");
	            System.out.println("Expected Text: [" + expectedText + "]");

	            // Assert without modifying spaces in between
	            Assert.assertEquals("Text does not match!", expectedText, actualText);
	            System.out.println("Assertion Passed!");

	        } catch (Exception e) {
	            System.err.println("Error occurred: " + e.getMessage());
	        }

	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30); // Selenium 4+
	            
	            WebElement sparent = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/showchild.svg']")));

	            // First Click - Activate "Show Parent"
	            sparent.click();
	            Thread.sleep(3000);
	            System.out.println("The show parent icon is clicked (1st time)");
	            Thread.sleep(3000);
	            System.out.println("The Structure icon is clicked");
	        } catch (Exception e) {
	            System.err.println("The Structure icon icon is not clicked: " + e.getMessage());
	        }
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30); // Selenium 4+
	            // Wait and Click Again - Activate "Show Parent Toggle"
	            WebElement sparent2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/showchildtoggle.svg']"))); // Re-locate the element
	            sparent2.click();
	            Thread.sleep(3000);
	            System.out.println("The show parent toggle icon is clicked (2nd time)");

	        } catch (Exception e) {
	            System.err.println("The show parent icon is not clicked: " + e.getMessage());
	        }
//	        try {
//	            WebDriverWait wait = new WebDriverWait(driver, 30);
//	            WebElement sparent = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/showchild.svg']")));
//	          
//	            sparent.click();
//	            Thread.sleep(3000);
//	            System.out.println("The show parent icon is clicked");
//	        } catch (Exception e) {
//	            System.err.println("The show parent icon is not clicked: " + e.getMessage());
//	        }
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            WebElement strucview = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/physical_view.svg']")));
	          
	            strucview.click();
	            Thread.sleep(3000);
	            System.out.println("The Structure icon is clicked");
	        } catch (Exception e) {
	            System.err.println("The Structure icon icon is not clicked: " + e.getMessage());
	        }
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            WebElement tissueview = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='/viewer/assets/images/colorsvg/logical_view.svg']")));
	          
	            tissueview.click();
	            Thread.sleep(3000);
	            System.out.println("The Tissue View icon is clicked");
	        } catch (Exception e) {
	            System.err.println("The Tissue View icon is not clicked: " + e.getMessage());
	        }
	    
	    }
	    
	    
	        @AfterTest
		    public void tearDown() {
		        if (driver != null) {
		            driver.quit();
		        }
		    }}

