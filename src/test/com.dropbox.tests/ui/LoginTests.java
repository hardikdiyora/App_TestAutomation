package ui;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends TestBase{

    private LoginPage _loginPage;

    @BeforeMethod
    void setUp(){
        _loginPage = new LoginPage(driver, wait);
    }

    @Parameters({"email","password"})
    @Test
    void loginSuccessFulWithValidCredentials(String email, String password){
        Log.info("Verify a user is able to login successfully.");
        HomePage _homePage = (HomePage) _loginPage.loginToAccount(email, password);
        Assert.assertTrue(_homePage.checkNavMenu());
        Log.info("Log out from Account.");
        _homePage.logOut();
    }

    @Test
    void emptyEmail_InLoginFormError(){
        Log.info("Verify a user is able to see proper error message related to email.");
        _loginPage.loginToAccount("", "QWERTY");
    }

    @Test
    void emptyPwd_InLoginFormError(){
        Log.info("Verify a user is able to see proper error message related to password.");
        _loginPage.loginToAccount("qazxsw@yahoo.com", "");
    }

    @Test
    void loginFailureWithWrongCredentials(){
        Log.info("Verify a user is able to see error message on wrong credentials.");
        HomePage _homePage = (HomePage) _loginPage.loginToAccount("rdop@wss.ddd", "dfgffe");
        Assert.assertFalse(_homePage.checkNavMenu());
    }
}
