package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.type.ReferenceVariableType;
import com.zomu.t.epion.tropic.test.tool.selenium.command.StartLocalWD;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import com.zomu.t.epion.tropic.test.tool.selenium.type.BrowserType;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * Selenium-WebDriver
 * ローカルWebDriverを起動する.
 *
 * @author takashno
 */
public class StartLocalWDRunner extends AbstractCommandRunner<StartLocalWD> {

    private static final String WEBDRIVER_PREFIX = "WEBDRIVER_";

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(
            StartLocalWD command,
            Logger logger) throws Exception {

        BrowserType browserType = BrowserType.valueOfByValue(command.getBrowser());

        if (browserType == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9001, command.getBrowser());
        }

        WebDriver.Options options = null;
        WebDriver driver = null;

        switch (browserType) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver",
                        command.getDriverPath());
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver",
                        command.getDriverPath());
                driver = new FirefoxDriver();
                break;
            case IE:
                System.setProperty("webdriver.ie.driver",
                        command.getDriverPath());
                driver = new InternetExplorerDriver();
                break;
            case PHANTOMJS:
                break;
            default:
                break;
        }

        if (command.getHeight() != null && command.getWidth() != null) {
            // ブラウザの高さ・幅を指定
            driver.manage().window().setSize(new Dimension(command.getWidth(), command.getHeight()));
        } else if ((command.getHeight() != null && command.getWidth() == null)
                || (command.getHeight() == null && command.getWidth() != null)) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9006);
        }

        // 変数設定
        String variableName = command.getTarget();

        // 指定してない場合は、自動で作り出す
        if (StringUtils.isEmpty(variableName)) {
            int count = 0;
            for (Map.Entry<String, Object> scenarioScopeVariable : getScenarioScopeVariables().entrySet()) {
                if (scenarioScopeVariable.getKey().startsWith(WEBDRIVER_PREFIX)) {
                    count++;
                }
            }
            variableName = ReferenceVariableType.SCENARIO.getName()
                    + "." + WEBDRIVER_PREFIX + new DecimalFormat("000").format(count);
        }
        setVariable(command.getTarget(), driver);

        return CommandResult.getSuccess();
    }

}
