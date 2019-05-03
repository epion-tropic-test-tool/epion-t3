package com.epion_t3.selenium.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.selenium.command.WDContainsUrlText;
import com.epion_t3.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class WDContainsUrlTextRunner extends AbstractCommandRunner<WDContainsUrlText> {
    @Override
    public CommandResult execute(
            WDContainsUrlText command,
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
