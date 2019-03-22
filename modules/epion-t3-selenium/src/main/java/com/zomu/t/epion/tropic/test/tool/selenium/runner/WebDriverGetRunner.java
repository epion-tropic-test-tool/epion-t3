package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverGet;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.util.Map;

public class WebDriverGetRunner extends AbstractCommandRunner<WebDriverGet> {
    @Override
    public CommandResult execute(WebDriverGet command, Logger logger) throws Exception {
        // WebDriverを取得
        WebDriver driver = resolveVariables(command.getRefWebDriver());
        // WebDriverが解決できない場合はエラー
        if (driver == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9007, command.getRefWebDriver());
        }
        driver.get(command.getTarget());
        return CommandResult.getSuccess();
    }
}
