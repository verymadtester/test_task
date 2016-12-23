package Utils;

import Controllers.IElement;
import org.openqa.selenium.WebElement;

/**
 * Created by Sergei_Shatilov on 12/22/2016.
 */
public class WrapperFactory {

    /**
     * Создает экземпляр класса,
     * реализующий IElement интерфейс,
     * вызывая конструктор с аргументом WebElement
     */
    public static IElement createInstance(Class<IElement> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).
                    newInstance(element);
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement can't be represented as " + clazz
            );
        }
    }

}
