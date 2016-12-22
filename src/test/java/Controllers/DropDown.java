package java.Controllers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public class DropDown extends BaseElement {

    public DropDown(WebElement webElement) {
        super(webElement);
    }

    @Override
    public void setValue(String  value){
        if (!isChecked()) {
            Select selectByValue = new Select(webElement);
            selectByValue.selectByValue(value);
        }
    }

    @Override
    public String getValue() {
        return webElement.getAttribute("value");
    }

    @Override
    public String getText(String value) {
        return value;
    }

    public boolean isChecked() {
        return webElement.isSelected();
    }

}
