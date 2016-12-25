package Pages;

import Controllers.DropDown;
import Controllers.Link;
import Controllers.Textbox;
import Utils.CustomFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Sergei_Shatilov on 12/23/2016.
 */
public class SearchPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//select[contains(@class, 'HH-Navi-SearchSelector-Select')]")
    public DropDown searchOptionsButton;

    @FindBy(how = How.XPATH, using = "//input[contains(@class, 'HH-EmployersSearchRedirect-Suggest')]")
    public Textbox searchPanel;

    @FindBy(how = How.XPATH, using = "//div[@class='b-alfabeta-totals nopaddings']/strong")
    public WebElement resultCount;

    @FindBy(how = How.XPATH, using = "//td[@class='l-cell b-companylist']//a")
    public Link searchResult;

    @FindBy(how = How.XPATH, using = "//div[@class='company-description__name']/h1")
    public WebElement companyTitle;

    @FindBy(how = How.XPATH, using = "//h4[@class='b-employerpage-vacancies-region']/span[contains(text(), 'Вакансии в текущем регионе:')]")
    public WebElement vacanciesRegion;

    @FindBy(how = How.XPATH, using = "//div[@class='b-employerpage-vacancies g-expand']//h4[@class='b-employerpage-vacancies-region']/span[2]")
    public WebElement vacanciesCount;

    @FindBy(how = How.XPATH, using = "//div[@class='b-employerpage-vacancies g-expand']//a[contains(text(), 'Информационные технологии, интернет, телеком')]")
    public Link itVacancies;

    public SearchPage(WebDriver driver){
        PageFactory.initElements(new CustomFieldDecorator(this, driver), this);
        this.driver = driver;
    }

    public WebDriver driver;

    public void selectSearchOption(String value){
        searchOptionsButton.click();
        searchOptionsButton.setValue(value);
    }

    public void findCompany(String value){
        searchPanel.setValue(value);
        pressEnterKey();
    }

    public void openCompanyPage(){
        searchResult.click();
    }

    public WebElement qaPosition(String positionName) {
        return driver.findElement(By.xpath("//div[@class='b-employerpage-vacancies g-expand']//a[contains(text(), '"+ positionName +"')]"));
    }
}
