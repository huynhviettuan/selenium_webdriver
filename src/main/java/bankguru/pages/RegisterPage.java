package bankguru.pages;

import commons.page.BasePage;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    public final String lblEmailID = "Email ID";
    public final String lblSubmit = "Submit";
    public final String lblUserIDValue = "//td[text()='User ID :']/following-sibling::td";
    public final String lblPasswordValue = "//td[text()='Password :']/following-sibling::td";

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillEmail(String email) {
        enterToDynamicTextBox(lblEmailID, email);
        clickToDynamicButton(lblSubmit);
    }

    public String getUserID(){
        waitForElementVisible(lblUserIDValue);
        return getTextElement(lblUserIDValue);
    }

    public String getPassword(){
        waitForElementVisible(lblPasswordValue);
        return getTextElement(lblPasswordValue);
    }

}
