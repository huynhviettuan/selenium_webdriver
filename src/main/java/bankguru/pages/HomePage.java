package bankguru.pages;

import commons.page.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public final String lblUserID = "UserID";
    public final String lblPassword = "Password";
    public final String lblLogin = "LOGIN";
    public final String lblHere = "here";

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
