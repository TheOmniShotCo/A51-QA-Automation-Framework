import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework20 extends BaseTest {

    @Test
    public void deletePlaylist() throws InterruptedException{

        String uniquePlaylist = "1-HW19-Playlist";
        String deletePlaylistMsg = "Deleted playlist \"1-HW19-Playlist.\"";

        //Pre-condition
        //Login
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();

        //Click on empty playlist created
        clickOnPlaylist();

        //delete selected playlist
        clickOnDeleteBtn();

        //Expected Results
        WebElement notificationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alertify-logs div.success")));//driver.findElement(By.cssSelector("div.alertify-logs div.success"));

        Assert.assertEquals(deletePlaylistMsg,notificationMsg.getText());

    }

    public void clickOnPlaylist(){
        WebElement playList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists'] //li[contains( . , '1-HW19-Playlist')]")));

        playList.click();
    }

    public void clickOnDeleteBtn(){
        WebElement deleteBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='del btn-delete-playlist']")));//driver.findElement(By.xpath("//button[@class='del btn-delete-playlist']"));
        deleteBtn.click();
    }
}