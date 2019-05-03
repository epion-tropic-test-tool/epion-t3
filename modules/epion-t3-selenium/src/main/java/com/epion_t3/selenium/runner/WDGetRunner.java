package com.epion_t3.selenium.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.selenium.command.WDGet;
import com.epion_t3.selenium.message.SeleniumMessages;
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
