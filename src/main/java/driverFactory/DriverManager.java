package driverFactory;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class DriverManager {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected abstract WebDriver createDriver();

    public void quitDriver() {
        if (null != driver.get()) {
            try {
                driver.get().quit(); // First quit WebDriver session gracefully
                driver.remove(); // Remove WebDriver reference from the ThreadLocal variable.
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(this.createDriver());
        }
        driver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        return driver.get();
    }
}
