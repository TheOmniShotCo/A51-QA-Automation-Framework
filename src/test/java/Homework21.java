import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21 extends BaseTest {

    String newPlaylistName = "HW 21 - Sample Edited Playlist";


    @Test
    public void renamePlaylist() throws InterruptedException {
        //Prerequisite at least one user created playlist
        String updatedPlaylistMsg = "Updated playlist \"GabonHW 21 - Sample Edited Playlist.\"";

        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        doubleClickPlaylist();
        enterNewPlaylistName();

        Assert.assertEquals(getRenamePlaylistSuccessMsg(), updatedPlaylistMsg);
    }

    private void doubleClickPlaylist() {
        WebElement playListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playListElement).perform();
    }

    private void enterNewPlaylistName() {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        //clear() does not work, element has an attribute of "required"
        //workaround is ctrl A (to select all) then backspace to clear then replace with new playlist name
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));

        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    private String getRenamePlaylistSuccessMsg() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
    }
}
