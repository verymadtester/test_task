package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.stqa.selenium.factory.WebDriverFactory;

/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public class WebDiverConfig {

    public static WebDriver setFirefoxPofile(){

        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("capability.policy.policynames", "strict");
        profile.setPreference("capability.policy.strict.Window.alert", "noAccess");
        profile.setPreference("capability.policy.strict.Window.confirm", "noAccess");
        profile.setPreference("capability.policy.strict.Window.prompt", "noAccess");
        profile.setAcceptUntrustedCertificates(true);
        profile.setPreference("javascript.enabled", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        capabilities.setBrowserName("firefox");
        WebDriver driver = WebDriverFactory.getDriver(capabilities);
        return driver;
    }

}
