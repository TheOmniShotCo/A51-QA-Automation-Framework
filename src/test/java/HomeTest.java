import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomeTest extends BaseTest{

    String newPlaylistName = "Sample Edited Playlist";

    //region Test Cases
    @Test
    public void playSong() throws InterruptedException {
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        chooseAllSongsList();
        contextClickFirstSong();
        choosePlayOption();
        Assert.assertTrue(isSongPlaying());
    }

    @Test
    public void countSongsInPlayList(){
        //Prerequisite - user created playlist named "Playlist Demo" with at least 1 song
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        choosePlaylistByName("Playlist Demo");
        displayAllSongs();
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));
        //verifies if the playlist details song count is equal to the retrieved number of songs
    }

    @Test
    public void renamePlaylist(){
        //Prerequisite at least one user created playlist
        String updatedPlaylistMsg = "Updated playlist \"Sample Edited Playlist.\"";

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
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL,"A",Keys.BACK_SPACE));

        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    private String getRenamePlaylistSuccessMsg() {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
    }

    @Test
    public void hoverOverPlayButton(){
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        Assert.assertTrue(hoverPlay().isDisplayed());
    }
    //endregion

    //region Helper Methods
    private WebElement hoverPlay() {
        WebElement play = driver.findElement(By.cssSelector("[data-testid='play-btn'"));
        actions.moveToElement(play).perform();
        return wait.until(ExpectedConditions.visibilityOf(play));
    }

    private void chooseAllSongsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li a.songs"))).click();
    }

    private void contextClickFirstSong() {
        WebElement firstSongElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        actions.contextClick(firstSongElement).perform();
    }

    private void choosePlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.playback"))).click();
    }

    private boolean isSongPlaying() {
        WebElement soundBarVizualizer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid = 'sound-bar-play'")));
        return soundBarVizualizer.isDisplayed();
    }
    private void choosePlaylistByName(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'"+ playlistName +"')]"))).click();
    }

    private int countSongs() {//counts songs inside playlist
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();
    }

    private String getPlaylistDetails() {//retrieve playlist details from playlist header (displays number of songs in playlist)
        return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();
    }

    private void displayAllSongs() {//prints all songs inside the playlist - for demo purposes
        List<WebElement> songList = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of songs found: " + countSongs());
        for(WebElement e : songList) {
            System.out.println(e.getText());
        }

    }
    //endregion
}