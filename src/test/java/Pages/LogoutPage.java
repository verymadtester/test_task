package Pages;

import Controllers.Link;
import Utils.CustomFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public class LogoutPage extends BasePage{

    public LogoutPage(WebDriver driver){
        PageFactory.initElements(new CustomFieldDecorator(this, driver), this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"navi-item__switcher\")]/span[@class=\"navi-item__employer-info\"]")
    public Link username;

    @FindBy(how = How.XPATH, using = "//button[@class=\"navi-dropdown-link\"]")
    public Link logout;

    @FindBy(how = How.XPATH, using = "//span[@title=\"hh.ru\"]")
    public Link logo;

    public void openUserProfile(){
        username.click();
    }

    public void clickLogout(){
        logout.click();
        waitForJStoLoad();
        logo.isVisible();
    }

}
