package bankguru.pages;

import commons.page.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public String lblUserID = "UserID";
    public String lblPassword = "Password";
    public String lblLogin = "LOGIN";
    public String lblHere = "here";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void login(String userName, String password) {
        enterToDynamicTextBox(lblUserID, userName);
        enterToDynamicTextBox(lblPassword, password);
        clickToDynamicButton(lblLogin);

    }

    public void clickHereLink() {
        clickToDynamicLink(lblHere);
    }

}
