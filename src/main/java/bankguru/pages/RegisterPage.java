package bankguru.pages;

import commons.page.BasePage;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    public String lblUserID = "UserID";
    public String lblPassword = "Password";
    public String lblLogin = "Login";
    public String lblHere = "here";

    public RegisterPage(WebDriver driver) {
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
