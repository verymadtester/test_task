package Pages;

import Controllers.Button;
import Controllers.Link;
import Controllers.Textbox;
import Utils.Configuration;
import Utils.CustomFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public class LoginPage extends BasePage{

    @FindBy(how = How.XPATH, using = "//span/input[@name=\"username\"]")
    public Textbox loginField;

    @FindBy(how = How.XPATH, using = "//span/input[@name=\"password\"]")
    public Textbox passField;

    @FindBy(how = How.XPATH, using = "//div[@class=\"login-submit-form\"]/input[@type=\"submit\"]")
    public Button loginButton;

    @FindBy(how = How.XPATH, using = "//span[@title=\"hh.ru\"]")
    public Link logo;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(new CustomFieldDecorator(this, driver), this);
        this.driver = driver;
    }

    public WebDriver driver;

    protected Configuration getConfig() {
        return Configuration.getInstance();
    }

    public void loadPage() {
        driver.get("https://spb.hh.ru/");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlContains("https://spb.hh.ru/"));
        driver.manage().window().maximize();
    }

    public void clickLogin(){
        loginButton.click();
        waitForJStoLoad();
        logo.isVisible();
    }

}
