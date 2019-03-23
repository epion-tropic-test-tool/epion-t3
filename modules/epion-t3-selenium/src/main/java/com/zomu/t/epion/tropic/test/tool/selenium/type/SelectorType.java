package com.zomu.t.epion.tropic.test.tool.selenium.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SelectorType {

    ID("id", By.ById.class),
    CLASS_NAME("className", By.ByClassName.class),
    CSS_SELECTOR("cssSelector", By.ByCssSelector.class),
    LINK_TEXT("linkText", By.ByLinkText.class),
    NAME("name", By.ByName.class),
    XPATH("xpath", By.ByXPath.class),
    ;

    private String value;

    private Class<? extends By> by;

    public static SelectorType valueOfByValue(final String value) {
        return Arrays.stream(values()).filter(x -> x.value.equals(value)).findFirst().orElse(null);
    }
}
