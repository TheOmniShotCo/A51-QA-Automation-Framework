package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    //constructor
    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    //region Locators
    By userAvatarIcon = By.cssSelector("img.avatar");
    //endregion

    //region Helper Methods
    public WebElement getUserAvatar(){
        return findElement(userAvatarIcon);
    }
    //endregion

}