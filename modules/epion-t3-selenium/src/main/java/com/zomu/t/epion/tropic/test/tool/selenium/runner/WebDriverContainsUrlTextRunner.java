package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverContainsUrlText;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class WebDriverContainsUrlTextRunner extends AbstractCommandRunner<WebDriverContainsUrlText> {
    @Override
    public CommandResult execute(
            WebDriverContainsUrlText command,
            Logger logger) throws Exception {
        // WebDriverを取得
        WebDriver driver = resolveVariables(command.getRefWebDriver());
        // WebDriverが解決できない場合はエラー
        if (driver == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9007, command.getRefWebDriver());
        }
        if (!driver.getCurrentUrl().contains(command.getTarget())) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9005, command.getTarget());
        }

        return CommandResult.getSuccess();
    }
}
