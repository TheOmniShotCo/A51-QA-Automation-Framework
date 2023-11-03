import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;



public class Homework16 extends BaseTest {

    @Test
    public void registrationNavigation() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://qa.koel.app/";
        driver.get(url);

        WebElement registrationLink = driver.findElement(By.cssSelector("[href='registration']"));
        registrationLink.click();

        String registrationURL = "https://qa.koel.app/registration";
        Assert.assertEquals(driver.getCurrentUrl(), registrationURL);

        driver.quit();

    }
}