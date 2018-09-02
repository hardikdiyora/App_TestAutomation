package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilesPage extends BasePage {

    private static By files_tb = By.cssSelector("table[class*='files-view']");
    private static By file_name = By.cssSelector("a[class*='-file-name-']");
    private static By file_actions_menu = By.cssSelector("button[class*='overflow-menu-trigger']");
    private static By action_delete = By.cssSelector("button[class^='action-delete']");
    private static By action_modal = By.cssSelector("div[class^='mc-modal-body ']");
    private static By action_primary_btn = By.cssSelector("button[class$='-primary']");
    private static By notify_msg = By.cssSelector("span[class='notify-msg']");

    private static By create_folder = By.cssSelector("div[class$='-new-folder']");
    private static By new_folder_name = By.cssSelector("input[class='c-input']");
    private static By title = By.cssSelector("span[class$='HomeTitle']");


    FilesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /***
     * delete file or folder using menu action.
     * @param name name of file or folder
     */
    public void deleteFileOrFolder(String name){
        if(isFileOrFolderPresent(name))
        {
            for (WebElement row : getRowsInTable(files_tb)) {
                if (row.findElement(file_name).getText().equals(name)) {
                    row.findElement(file_actions_menu).click();
                    confirmDeleteAction();
                    break;
                }
            }
        }
    }

    /***
     * check whether file or folder is listed in table
     * @param name name of file or folder
     * @return
     */
    public Boolean isFileOrFolderPresent(String name) {
        driver.navigate().refresh();
        if(isElementPresent(files_tb)){
            for (WebElement row : getRowsInTable(files_tb)) {
                if (row.findElement(file_name).getText().equals(name)) {
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }

    /***
     * create folder
     * @param name name of folder
     */
    public void createFolder(String name){
        waitUntilElementVisible(create_folder);
        click(create_folder);
        inputText(new_folder_name, name);
        driver.findElement(title).click();
        waitUntilElementVisible(notify_msg);
    }

    private void confirmDeleteAction(){
        click(action_delete);
        waitUntilElementVisible(action_modal);
        click(action_primary_btn);
        waitUntilElementVisible(notify_msg);
    }


}
