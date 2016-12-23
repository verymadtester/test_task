package Controllers;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public abstract class BaseElement implements IElement {

    protected WebElement webElement;

    public BaseElement(WebElement webElement) {
        this.webElement = webElement;
    }


    public WebElement getWebElement() {
        return webElement;
    }

    public boolean isEnable() {
        if (isVisible()) {
            return webElement.isEnabled();
        }else {
            throw new ElementNotVisibleException("WebElement is not visible");
        }
    }

    public boolean isVisible(){
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void click(){

        if (webElement.isEnabled()){
            webElement.click();
        }else {
            throw new NoSuchElementException("Element is disable");
        }
    }

}
