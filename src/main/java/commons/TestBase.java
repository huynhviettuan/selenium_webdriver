package commons;

import driverFactory.DriverManager;
import driverFactory.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class TestBase {

    private DriverManager driverManager;
    public WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void init(String browserName) {
        driverManager = DriverManagerFactory.getDriverManager(browserName);
        driver = driverManager.getDriver();
        driver.get("http://demo.guru99.com/v4/");
    }

    @AfterClass
    public void close(){
        driverManager.quitDriver();
    }

}
