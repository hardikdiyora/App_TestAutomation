package ui;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.FilesPage;
import pages.HomePage;
import pages.LoginPage;

public class FolderTests extends TestBase {
    private FilesPage _filesPage;

    @Parameters({"email","password"})
    @BeforeMethod
    void setUp(String email, String password){
        LoginPage _loginPage = new LoginPage(driver, wait);
        HomePage _homePage = (HomePage)_loginPage.loginToAccount(email, password);
        _filesPage = _homePage.navigateToFiles();
    }

    @Test(priority = 0)
    void createFolderSuccessFully(){
        Log.info("Verify user able create new folder successfully.");
        _filesPage.createFolder("Temp");
        Assert.assertTrue(_filesPage.isFileOrFolderPresent("Temp"));
    }

    @Test(priority = 1)
    void deleteFolderSuccessFully(){
        Log.info("Verify user able to delete folder successfully.");
        _filesPage.deleteFileOrFolder("Temp");
        Assert.assertFalse(_filesPage.isFileOrFolderPresent("Temp"));
    }

    @AfterMethod
    void tearDown(){
        Log.info("Logout from web application.");
        _filesPage.logOut();
    }

}
