import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Test Class to play song directly from play button
 */
public class Homework18 extends BaseTest {

    /**
     * Test Case to Play a song by pressing Play button without selecting a song first
     */
    @Test
    public void playSong() throws InterruptedException{

        //Pre-condition
        navigateToPage();
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();

        //Steps
        clickPlayNextSongBtn();

        try {

            waitForClickablePlayBtn();

        }
        catch (TimeoutException e){

            clickPlayNextSongBtn();
            waitForClickablePlayBtn();

        }
        finally
        {
            clickplayBtn();
        }

        //Expected Result
        WebElement visualizerBtn = driver.findElement(By.xpath("//button[@data-testid=\"toggle-visualizer-btn\"]"));
        Assert.assertTrue(visualizerBtn.isDisplayed());

    }

    /**
     * Finds the element for the button used to play the next song
     * and clicks on it once it finds it.
     */
    private void clickPlayNextSongBtn() {
        WebElement playNextSongBtn = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        playNextSongBtn.click();
    }

    /**
     * Finds the element for the button used to play a song
     * and clicks on it once it finds it.
     */
    private void clickplayBtn() {
        WebElement playSongBtn = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        playSongBtn.click();
    }

    /**
     * Forces the process to wait until play button element is clickable before trying to click.
     */
    private void waitForClickablePlayBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-testid='play-btn']")));
    }

}