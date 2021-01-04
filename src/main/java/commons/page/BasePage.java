package commons.page;

import commons.Constants;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private Logger logger = Logger.getLogger(BasePage.class);

    public By getByLocator(String locator) {
        String body = locator.replaceAll("[\\w\\s]*=(.*)", "$1").trim();
        String type = locator.replaceAll("([\\w\\s]*)=.*", "$1").trim();
        switch (type.toLowerCase()) {
            case "css":
                return By.cssSelector(body);
            case "id":
                return By.id(body);
            case "class":
                return By.className(body);
            case "link":
                return By.linkText(body);
            case "xpath":
                return By.xpath(body);
            case "text":
                return By.xpath(String.format("//*[contains(text(), '%s')]", body));
            case "name":
                return By.name(body);
            default:
                return By.xpath(locator);
        }
    }

    public void openAnyURL(String urlValue) {
        driver.get(urlValue);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentPageURL() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void backToPage() {
        driver.navigate().back();
    }

    public void refreshCurrentPage() {
        driver.navigate().refresh();
    }

    public void fowardToPage() {
        driver.navigate().forward();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getTextAlert() {
        return driver.switchTo().alert().getText();
    }

    public void sendkeyToAlert(String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public void clickToElement(String locator) {
        element = driver.findElement(getByLocator(locator));
        element.click();
    }

    public void clickToElement(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        element = driver.findElement(getByLocator(locator));
        element.click();
    }

    public void enterToElement(String locator, String value) {
        element = driver.findElement(getByLocator(locator));
        element.clear();
        element.sendKeys(value);
    }

    public void enterToElement(String locator, String sendkeyValue, String... values) {
        locator = String.format(locator, (Object[]) values);
        element = driver.findElement(getByLocator(locator));
        element.clear();
        element.sendKeys(sendkeyValue);
    }

    public void selectItemInDropdrown(String locator, String valueItem) {
        select = new Select(driver.findElement(getByLocator(locator)));
        select.selectByVisibleText(valueItem);
    }

    public void selectItemInDropdrown(String locator, String valueItem, String... values) {
        locator = String.format(locator, (Object[]) values);
        select = new Select(driver.findElement(getByLocator(locator)));
        select.selectByVisibleText(valueItem);
    }

    public String getFirstItemInDropdown(String locator) {
        select = new Select(driver.findElement(getByLocator(locator)));
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInDropdown(String parentLocator, String allItemLocator, String expectedItem) throws Exception {
        jsExecutor = (JavascriptExecutor) driver;
        element = driver.findElement(getByLocator(parentLocator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(2000);
        jsExecutor.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);

        waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
        waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemLocator)));

        elements = driver.findElements(getByLocator(allItemLocator));

        for (WebElement item : elements) {
            logger.debug(item.getText());
            if (item.getText().equals(expectedItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                Thread.sleep(1000);
                item.click();
                break;
            }
        }
    }

    public String getAttributeValue(String locator, String attributeName) {
        element = driver.findElement(getByLocator(locator));
        return element.getAttribute(attributeName);
    }

    public String getTextElement(String locator) {
        element = driver.findElement(getByLocator(locator));
        return element.getText();
    }

    public String getTextElement(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        element = driver.findElement(getByLocator(locator));
        return element.getText();
    }

    public void countNumberOfElement(String locator) {
        elements = driver.findElements(getByLocator(locator));
        elements.size();
    }

    public void overideImplicitTimeout(long timeInSecond) {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public boolean isElementDisplayed(String locator) {
        overideImplicitTimeout(Constants.SHORT_TIMEOUT);
        try {
            element = driver.findElement(getByLocator(locator));
            overideImplicitTimeout(Constants.LONG_TIMEOUT);
            return element.isDisplayed();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            overideImplicitTimeout(Constants.LONG_TIMEOUT);
            return false;
        }
    }

    public boolean isElementDisplayed(String locator, String... values) {
        overideImplicitTimeout(Constants.SHORT_TIMEOUT);
        try {
            locator = String.format(locator, (Object[]) values);
            element = driver.findElement(getByLocator(locator));
            overideImplicitTimeout(Constants.LONG_TIMEOUT);
            return element.isDisplayed();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            overideImplicitTimeout(Constants.LONG_TIMEOUT);
            return false;
        }
    }

    public boolean isElementSelected(String locator) {
        element = driver.findElement(getByLocator(locator));
        return element.isSelected();
    }

    public boolean isElementEnabled(String locator) {
        element = driver.findElement(getByLocator(locator));
        return element.isEnabled();
    }

    public void checkToCheckBox(String locator) {
        element = driver.findElement(getByLocator(locator));
        if (!isElementSelected(locator)) {
            clickToElement(locator);
        }
    }

    public void uncheckToCheckBox(String locator) {
        element = driver.findElement(getByLocator(locator));
        if (isElementSelected(locator)) {
            clickToElement(locator);
        }
    }

    public void switchToChildWindowByTitle(String title) {
        allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }

        }
    }

    public void hoverToElement(String locator) {
        action = new Actions(driver);
        element = driver.findElement(getByLocator(locator));
        action.moveToElement(element).perform();
    }

    public void doubleClickToElement(String locator) {
        action = new Actions(driver);
        element = driver.findElement(getByLocator(locator));
        action.doubleClick(element).perform();
    }

    public void rightClickToElement(String locator) {
        action = new Actions(driver);
        element = driver.findElement(getByLocator(locator));
        action.contextClick(element).perform();
    }

    public void dragAndDropElement(String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        WebElement sourcElement = driver.findElement(getByLocator(sourceLocator));
        WebElement targetElement = driver.findElement(getByLocator(targetLocator));
        action.dragAndDrop(sourcElement, targetElement).perform();
    }

    public void enterKeyboardToElement(String locator, Keys key) {
        action = new Actions(driver);
        element = driver.findElement(getByLocator(locator));
        action.sendKeys(element, key).perform();
    }

    public Object executeForBrowserByJS(String javaSript) {
        return jsExecutor.executeScript(javaSript);
    }

    public boolean verifyTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        logger.debug("Text actual = " + textActual);
        return textActual.equals(textExpected);
    }

    public boolean verifyTextInInnerTextContains(String textExpected) {
        return (boolean) jsExecutor.executeScript("return document.documentElement.innerText.contains('" + textExpected + "')");
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public Object executeForElementByJS(String javaSript) {
        return jsExecutor.executeScript(javaSript, element);
    }

    public void clickToElementByJS(String locator) {
        element = driver.findElement(getByLocator(locator));
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(String locator) {
        element = driver.findElement(getByLocator(locator));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sendkeyToElementByJS(String locator, String value) {
        element = driver.findElement(getByLocator(locator));
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        element = driver.findElement(getByLocator(locator));
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public void removeAttributeInDOM(String locator, String attributeRemove, String... values) {
        jsExecutor = (JavascriptExecutor) driver;
        locator = String.format(locator, (Object[]) values);
        element = driver.findElement(getByLocator(locator));
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public boolean closeAllWindowsWithoutParent(String parentWindow) {
        allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentWindow)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        return driver.getWindowHandles().size() == 1;
    }

    public void waitForElementVisible(String locator) {
        waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
        waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementPresence(String locator) {
        waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
        waitExplicit.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementNotPresence(String locator) {
        waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
        waitExplicit.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(getByLocator(locator))));
    }

    public void waitForElementClickable(String locator) {
        waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
        waitExplicit.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementInvisible(String locator) {
        waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
        try {
            waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void waitForAlertPresence() {
        waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
        waitExplicit.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForAjax() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);

            wait.until(driver -> {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                Boolean ajaxIsComplete = (Boolean) (executor.executeScript(
                        "if (typeof jQuery != 'undefined') { return jQuery.active == 0; } else {  return true; }"));
                Boolean domIsComplete = (Boolean) (executor
                        .executeScript("return document.readyState == 'complete';"));
                return domIsComplete && ajaxIsComplete;
            });
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void enterToDynamicTextBox(String elementName, String inputValue) {
        waitForElementVisible(BasePageUI.dynamicTextBox, elementName);
        enterToElement(BasePageUI.dynamicTextBox, inputValue, elementName);
    }

    public void clickToDynamicLink(String elementName) {
        waitForElementVisible(BasePageUI.dynamicLink, elementName);
        clickToElement(BasePageUI.dynamicLink, elementName);
    }

    public void clickToDynamicButton(String elementName) {
        waitForElementVisible(BasePageUI.dynamicButton, elementName);
        clickToElement(BasePageUI.dynamicButton, elementName);
    }

    Select select;
    Actions action;
    WebElement element;
    Set<String> allWindows;
    List<WebElement> elements;
    WebDriverWait waitExplicit;
    JavascriptExecutor jsExecutor;
}
