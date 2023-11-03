import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver = null;
    public WebDriverWait wait = null;
    public static Actions actions = null;
    public  String url = "https://qa.koel.app/";

    @BeforeSuite //The annotated method will run before all tests in this suit have run
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod //The annotated method will be run before all the test methods in the current class have been run.
    @Parameters({"BaseURL"})
    public void  launchBrowser(String BaseURL){
        //Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        actions = new Actions(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        url = BaseURL;
        navigateToPage();

    }
    @AfterMethod //The annotated method will be run after each test method
    public void closeBrowser(){
        driver.quit();
    }

    //region Helper Methods
    protected void navigateToPage() {
        driver.get(url);
    }

    protected void provideEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));//driver.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    protected void providePassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));//driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    protected void clickSubmit() {
        WebElement submitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']"))); //driver.findElement(By.cssSelector("button[type='submit']"));
        submitBtn.click();
    }
    //endregion
}