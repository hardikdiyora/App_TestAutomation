package pages;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger Log = Logger.getLogger(BasePage.class);

    public static By avtar_menu = By.cssSelector("div[class='mc-avatar']");
    public static By logout_btn = By.cssSelector("a[href$='logout']");
    public static By product_nav_bar = By.cssSelector("div[class*='nav__product']");
    public static By files_link = By.cssSelector("a[id='files']");

    public BasePage (WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    /****
     * click on the element.
     * @param locator locator of web element.
     ****/
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    /***
     * wait until element to be visible.
     * @param locator locator of web element.
     */
    public void waitUntilElementVisible(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /***
     * enter the text into text box.
     * @param locator locator of web element.
     * @param text text to be fill in web element like textbox or text area.
     *
     */
    public void inputText(By locator, String text) {
        waitUntilElementVisible(locator);
        driver.findElement(locator).sendKeys(text);
    }

    /***
     * Check whether element is present or not.
     *  @param locator locator of web element.
     */
    public boolean isElementPresent(By locator) {
        try {
            waitUntilElementVisible(locator);
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * enter the text into text box.
     * @param tableLocator locator of web element.
     * @return row elements of table
     */
    public List<WebElement> getRowsInTable(By tableLocator)
    {
        waitUntilElementVisible(tableLocator);
        WebElement table = driver.findElement(tableLocator);
        By rowsXPath = By.xpath(".//tbody//tr");
        return table.findElements(rowsXPath);
    }

    /***
     * navigate to files page
     * @return file page object
     */
    public FilesPage navigateToFiles(){
        waitUntilElementVisible(files_link);
        click(files_link);
        return new FilesPage(driver, wait);
    }

    /***
     * logout from web application
     */
    public LoginPage logOut(){
        click(avtar_menu);
        waitUntilElementVisible(logout_btn);
        click(logout_btn);
        return new LoginPage(driver, wait);
    }

}
