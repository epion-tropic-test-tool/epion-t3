package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDGet;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class WDGetRunner extends AbstractWDCommandRunner<WDGet> {
    @Override
    public CommandResult execute(WDGet command, Logger logger) throws Exception {
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
