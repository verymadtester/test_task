package Tests;

import Controllers.IElement;
import Pages.LoginPage;
import Pages.LogoutPage;
import Pages.SearchPage;
import Utils.PageObjectFactory;
import Utils.TestListener;
import Utils.WebDiverConfig;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Sergei_Shatilov on 12/23/2016.
 */
@Listeners({TestListener.class})
public class hhTests extends BasicTest {

    public WebDriver driver = WebDiverConfig.setFirefoxPofile();
    private String userName;
    private String password;

    LoginPage loginPage = PageObjectFactory.getPageObject(LoginPage.class);
    LogoutPage logoutPage = PageObjectFactory.getPageObject(LogoutPage.class);
    SearchPage searchPage = PageObjectFactory.getPageObject(SearchPage.class);
//        SentLettersPage sentLettersPage = PageObjectFactory.getPageObject(SentLettersPage.class);


    @Factory(dataProvider = "empLogin", dataProviderClass = BasicTest.class)
    public hhTests(String userName, String password) {
        this.userName = userName;
        this.password = password;
        System.out.println("Test with:" + userName);
    }

    @BeforeClass
    public void aLogin() {
        Map<IElement, String> map = new LinkedHashMap<>();
        map.put(loginPage.passField, password);
        map.put(loginPage.loginField, userName);
        loginPage.loadPage();
        loginPage.fillForm(map);
        loginPage.clickLogin();
        System.out.println("Before class executed");
    }

    @Test
    public void a_findCompany(){
        searchPage.selectSearchOption("employersList");
        searchPage.findCompany("новые облачные");
        Assert.assertEquals(searchPage.resultCount.getText(), "1");
        Assert.assertEquals(searchPage.searchResult.getText(), "Новые Облачные Технологии");
    }

    @Test
    public void b_countOfCompanyVacancies(){
        searchPage.openCompanyPage();
        Assert.assertEquals(searchPage.companyTitle.getText(), "Новые Облачные Технологии ");
        Assert.assertThat(searchPage.vacanciesRegion.getText(), CoreMatchers.containsString("Северо-Западный округ"));
        Assert.assertEquals(searchPage.vacanciesCount.getText(), "12");
    }

    @Test
    public void c_findQAVacancies(){
        searchPage.itVacancies.click();
        Assert.assertEquals(searchPage.qaPosition("QA Automation Engineer (Server)").getText(), "QA Automation Engineer (Server)");
        Assert.assertEquals(searchPage.qaPosition("Senior QA Automation Engineer").getText(), "Senior QA Automation Engineer");
    }

    @AfterClass
    public void logout() {
        logoutPage.openUserProfile();
        logoutPage.clickLogout();
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}
