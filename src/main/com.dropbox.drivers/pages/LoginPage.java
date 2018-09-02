package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private static By sign_in_btn = By.cssSelector("button[class*=' signin-button ']");
    private static By error_msg = By.cssSelector("span[class='error-message']");
    private static By email_tb = By.cssSelector("input[name='login_email']");
    private static By password_tb = By.cssSelector("input[name='login_password']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        waitUntilElementVisible(sign_in_btn);
    }

    /***
     * sign in to account with email and password.
     * @param email account email
     * @param password account password
     * @return Base Page
     */
    public BasePage loginToAccount(String email, String password) {
        inputText(email_tb, email);
        inputText(password_tb, password);
        click(sign_in_btn);
        if (email.equals("")) {
            Log.info("Email field has empty value.");
            validateError("Please enter your email address");
            return new LoginPage(super.driver, super.wait);
        } else if (password.equals("")) {
            Log.info("Password field has empty value.");
            validateError("Please enter your password");
            return new LoginPage(super.driver, super.wait);
        }
        else {
              return new HomePage(super.driver, super.wait);
        }
    }

    private void validateError(String msg){
        waitUntilElementVisible(error_msg);
        Assert.assertTrue(driver.findElement(error_msg).getText().equalsIgnoreCase(msg));
    }
}
