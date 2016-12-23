package Pages;

import Controllers.IElement;
import Utils.WebDiverConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public class BasePage {
    /**
     *
     * @param inputData
     */
    public void fillForm(Map<IElement, String> inputData){
        for (IElement element: elements){

            String value = inputData.get(element);
            if (element != null){
                if (value != null) {
                    element.setValue(value);
                }
            } else {
                throw new IllegalArgumentException("Arguments can't be null");
            }

        }
    }

    public void pressEnterKey(){
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public boolean waitForJStoLoad() {

        WebDriverWait wait = new WebDriverWait(WebDiverConfig.setFirefoxPofile(), 30);
        JavascriptExecutor jse = (JavascriptExecutor) WebDiverConfig.setFirefoxPofile();
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)jse.executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jse.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public void setFrame(Frame frame){
        this.frame = frame;
    }

    public Frame getFrame(){
        return frame;
    }

    public java.util.List<IElement> getElements(){
        return elements;
    }

    public void setElements(){
        this.elements = elements;
    }

    private java.util.List<IElement> elements = new ArrayList<>();
    private Frame frame;

}
