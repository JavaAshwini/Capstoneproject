import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
public class SauceDemoTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver (you might want to specify the path to the chromedriver)
        WebDriverManager.chromedriver().setup();
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @BeforeSuite
    public void testLaunchAndVerifyTitle() {
        // Launch the URL
        driver.get("https://www.saucedemo.com/");

        // Verify the title
        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match.");
    }

    @Test
    @Parameters({"username", "password"})
    public void testLoginWithValidCredentials(String username, String password) {
        driver.get("https://www.saucedemo.com/");

        // Enter username
        WebElement userField = driver.findElement(By.id("user-name"));
        userField.sendKeys(username);

        // Enter password
        WebElement passField = driver.findElement(By.id("password"));
        passField.sendKeys(password);

        // Click the login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Verify that user is logged in (check if the page title matches)
        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
        Assert.assertEquals(actualTitle, expectedTitle, "Login failed, title does not match.");
    }

    @Test
    @Parameters({"invalidUsername", "invalidPassword"})
    public void testLoginWithInvalidCredentials(String invalidUsername, String invalidPassword) {
        driver.get("https://www.saucedemo.com/");

        // Enter invalid username
        WebElement userField = driver.findElement(By.id("user-name"));
        userField.sendKeys(invalidUsername);

        // Enter invalid password
        WebElement passField = driver.findElement(By.id("password"));
        passField.sendKeys(invalidPassword);

        // Click the login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Verify that login fails (check if error message is displayed)
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed.");
    }

    @Test
    @Parameters({"username", "password"})
    public void testVerifyPageNavigationForPositiveCase(String username, String password) {
        driver.get("https://www.saucedemo.com/");

        // Enter username
        WebElement userField = driver.findElement(By.id("user-name"));
        userField.sendKeys(username);

        // Enter password
        WebElement passField = driver.findElement(By.id("password"));
        passField.sendKeys(password);

        // Click login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Verify navigation (title should be "Swag Labs")
        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
        Assert.assertEquals(actualTitle, expectedTitle, "User is not navigated to the correct page.");
    }

    @Test
    @Parameters({"invalidUsername", "invalidPassword"})
    public void testVerifyPageNavigationForNegativeCase(String invalidUsername, String invalidPassword) {
        driver.get("https://www.saucedemo.com/");

        // Enter invalid username
        WebElement userField = driver.findElement(By.id("user-name"));
        userField.sendKeys(invalidUsername);

        // Enter invalid password
        WebElement passField = driver.findElement(By.id("password"));
        passField.sendKeys(invalidPassword);

        // Click login
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Verify the page did not navigate to the right one
        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
        Assert.assertNotEquals(actualTitle, expectedTitle, "User is incorrectly navigated to the right page.");
    }

    @AfterMethod
    public void tearDown() {
        // Close the driver after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
