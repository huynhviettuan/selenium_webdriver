package bankguru;

import bankguru.pages.HomePage;
import commons.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleTCs extends TestBase {

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
    }

    @Test(description = "Login")
    public void TC_01_Login() {
        homePage.login("mngr302569","tezYtug");
    }

    public HomePage homePage;
}
