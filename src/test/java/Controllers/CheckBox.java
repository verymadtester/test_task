package java.Controllers;

import org.openqa.selenium.WebElement;

/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public class CheckBox extends BaseElement {

    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public void setChecked(boolean value) {
        if (value != isChecked()) {
            click();
        }
    }

    public boolean isChecked() {
        return webElement.isSelected();
    }

    @Override
    public void setValue(String value) {
        if (Boolean.parseBoolean(value) != isChecked()) {
            webElement.click();
        }
    }

    @Override
    public String getValue() {
        return String.valueOf(isChecked());
    }

    @Override
    public String getText(String value) {
        return value;
    }
}
