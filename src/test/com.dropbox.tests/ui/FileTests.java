package ui;

import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.FilesPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;

public class FileTests extends TestBase {

    private FilesPage _filesPage;

    @Parameters({"email","password"})
    @BeforeMethod
    void setUp(String email, String password){
        LoginPage _loginPage = new LoginPage(driver, wait);
        HomePage _homePage = (HomePage) _loginPage.loginToAccount(email, password);
        _filesPage = _homePage.navigateToFiles();
    }

    @Parameters({"access_token"})
    @Test(priority = 0)
    void uploadFileSuccessFully(String access_token) throws IOException {
        Log.info("Uploading File using DropBox API.");
        Assert.assertEquals(uploadFileUsingApi("Floor.jpg", access_token),200);
        Log.info("Verify File uploaded successfully file.");
        Assert.assertTrue(_filesPage.isFileOrFolderPresent("Floor.jpg"));
    }

    @Test(priority = 1)
    void deleteFileSuccessFully(){
        Log.info("Verify user able to delete file successfully.");
        _filesPage.deleteFileOrFolder("Floor.jpg");
        Assert.assertFalse(_filesPage.isFileOrFolderPresent("Floor.jpg"));
    }

    @AfterMethod
    void tearDown(){
        Log.info("Logout from web application.");
        _filesPage.logOut();
    }

    private int uploadFileUsingApi(String file_path, String access_token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        File file = new File(file_path);
        MediaType FILE = MediaType.parse("application/octet-stream");
        RequestBody body = RequestBody.create(FILE, file);
        Request request = new Request.Builder()
                .url("https://content.dropboxapi.com/2/files/upload")
                .post(body)
                .addHeader("Authorization", "Bearer "+access_token)
                .addHeader("dropbox-api-arg", "{\"path\":\"/Floor.jpg\",\"mode\":\"add\"," +
                        "\"autorename\":true,\"mute\":false,\"strict_conflict\":false}")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Content-Type", "application/octet-stream")
                .build();

        Response response = client.newCall(request).execute();
        return response.code();
    }
}
