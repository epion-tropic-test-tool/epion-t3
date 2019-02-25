package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverClick;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverContainsUrlText;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.Map;

public class WebDriverContainsUrlTextRunner extends AbstractCommandRunner<WebDriverContainsUrlText> {
    @Override
    public CommandResult execute(
            WebDriverContainsUrlText process,
            Logger logger) throws Exception {
        WebDriver driver = WebDriver.class.cast(getGlobalScopeVariables().get(process.getRefWebDriver()));
        if (!driver.getCurrentUrl().contains(process.getTarget())) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9005, process.getTarget());
        }

        return CommandResult.getSuccess();
    }
}
