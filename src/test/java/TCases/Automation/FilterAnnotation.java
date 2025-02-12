package TCases.Automation;

import static org.junit.Assert.assertTrue;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FilterAnnotation {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        URL url = new URL("http://172.20.23.92:4444/wd/hub");  // Update with your Grid URL
        driver = new RemoteWebDriver(url, dc);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

            // Handle login popup
            String parentWindow = driver.getWindowHandle();
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Log In ']")));
            loginButton.click();

            Set<String> allWindows = driver.getWindowHandles();
            for (String window : allWindows) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']")));
            emailInput.sendKeys("softwaretestingteam9@gmail.com");

            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
            nextButton.click();

            WebElement passwordInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
            passwordInput.sendKeys("Health#123");

            WebElement nextButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));
            nextButton2.click();

            driver.switchTo().window(parentWindow);
            Thread.sleep(5000);
        } catch (Exception e) {
            System.err.println("An error occurred during login: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void filterAnnotation() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search tags']")));
            searchBox.sendKeys("222\n");
            Thread.sleep(3000);
            System.out.println("-------------------------------------------------");
            System.out.println("The number is entered successfully");

            WebElement viewerIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//nb-icon[@icon='viewer']")));
            viewerIcon.click();
            Thread.sleep(5000);
            System.out.println("The Viewer icon is clicked");

            // Check if iframe exists and switch
            if (isElementPresent(By.tagName("iframe"))) {
                driver.switchTo().frame(0);
                System.out.println("Switched to iframe.");
            }

            // Locate the 'Filter Annotation Sections' button
            WebElement filterAnnotationButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Filter Annotation Sections']")));
            wait.until(ExpectedConditions.elementToBeClickable(filterAnnotationButton));

            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filterAnnotationButton);
            Thread.sleep(1000);

            // Click using normal and JavaScript fallback
            try {
                filterAnnotationButton.click();
                System.out.println("Filter Annotation button clicked successfully.");
            } catch (ElementClickInterceptedException e) {
                System.out.println("Normal click failed, trying JavaScript click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", filterAnnotationButton);
            }

            // Switch back to main content if iframe was used
            driver.switchTo().defaultContent();

            // API validation
            String apiUrl = "https://apollo2.humanbrain.in/GW/getSectionAnnotatedDetails/IIT/V1/SS-100:-1:-1/1";
            int statusCode = validateAPI(apiUrl);
            Assert.assertEquals(statusCode, 200, "API validation failed.");

        } catch (Exception e) {
            System.out.println("-------------------------------------------------");
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private boolean isElementPresent(By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private int validateAPI(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            System.out.println("API Response Code: " + responseCode);
            return responseCode;
        } catch (Exception e) {
            System.err.println("Failed to call the API: " + e.getMessage());
            return -1; // Return -1 to indicate failure
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully");
        }
    }
}
