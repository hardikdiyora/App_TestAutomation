package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

    private static By nav_bar = By.cssSelector("div[class*='nav__product']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /***
     * check navigation menu is present.
     *
     */
    public Boolean checkNavMenu(){
        Log.info("Checking the Priduct navigation menu is present.");
        return isElementPresent(nav_bar);
    }

}
