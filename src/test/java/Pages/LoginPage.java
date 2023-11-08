package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {


    //constructor
    public LoginPage(WebDriver givenDriver){
        super(givenDriver);
    }

    //region Locators
    By emailField = By.cssSelector("input[type='email']");

    By passwordField = By.cssSelector("input[type='password']");

    By submitBtn = By.cssSelector("button[type='submit']");
    //endregion

    //region Helper Methods
    public void provideEmail(String email) {
        findElement(emailField).sendKeys(email);
    }
    public void providePassword(String password) {
        findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit() {
        findElement(submitBtn).click();
    }

    public void login(){
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
    }
    //endregion
}
