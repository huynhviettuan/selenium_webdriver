package bankguru;

import bankguru.pages.HomePage;
import bankguru.pages.RegisterPage;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.relevantcodes.extentreports.LogStatus;
import commons.Log;
import commons.TestBase;
import commons.report.ExtentTestManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Locale;

public class SampleTCs extends TestBase {

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        email = fakeValuesService.bothify("????##@gmail.com");
    }

    @Test(description = "Login")
    public void TC_01_Login(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login");

        Log.info("Step 1 - Click to Here Link");
        homePage.clickHereLink();

        Log.info("Step 2 - Fill email account");
        registerPage.fillEmail(email);

        Log.info("Step 3 - Get Account Info");
        userID = registerPage.getUserID();
        password = registerPage.getPassword();

        Log.info("Step 4 - Back to Home page");
        registerPage.openAnyURL("http://demo.guru99.com/v4/");

        Log.info("Step 5 - Login with created account");
        homePage.login(userID, password);

        ExtentTestManager.endTest();
    }

    public HomePage homePage;
    public RegisterPage registerPage;
    public String email;
    public String userID;
    public String password;
}
