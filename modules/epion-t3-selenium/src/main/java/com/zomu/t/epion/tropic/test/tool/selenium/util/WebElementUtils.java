package com.zomu.t.epion.tropic.test.tool.selenium.util;

import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.util.DateTimeUtils;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import com.zomu.t.epion.tropic.test.tool.selenium.type.SelectorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class WebElementUtils {

    private static final WebElementUtils instance = new WebElementUtils();

    public static WebElementUtils getInstance() {
        return instance;
    }

    private WebElementUtils() {
        // Do Nothing...
    }

    public WebElement findWebElement(WebDriver driver, String selector, String target) {

        SelectorType selectorType = SelectorType.valueOfByValue(selector);

        WebElement element = null;
        switch (selectorType) {
            case ID:
                element = driver.findElement(By.id(target));
                break;
            case CLASS_NAME:
                element = driver.findElement(By.className(target));
                break;
            case CSS_SELECTOR:
                element = driver.findElement(By.cssSelector(target));
                break;
            case LINK_TEXT:
                element = driver.findElement(By.linkText(target));
                break;
            case NAME:
                element = driver.findElement(By.name(target));
                break;
            default:
                throw new SystemException(SeleniumMessages.SELENIUM_ERR_9004, selector);
        }
        return element;
    }

}
