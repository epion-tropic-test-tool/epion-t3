package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.selenium.command.StartLocalWebDriver;
import com.zomu.t.epion.tropic.test.tool.selenium.type.BrowserType;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * Selenium-WebDriver
 * ローカルWebDriverを起動する.
 *
 * @author takashno
 */
public class StartLocalWebDriverRunner extends AbstractCommandRunner<StartLocalWebDriver> {

    private static final String WEBDRIVER_PREFIX = "WEBDRIVER_";

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(
            StartLocalWebDriver process,
            Logger logger) throws Exception {

        BrowserType browserType = BrowserType.valueOfByValue(process.getBrowser());

        if (browserType == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9001, process.getBrowser());
        }

        WebDriver.Options options = null;
        WebDriver driver = null;

        switch (browserType) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver",
                        process.getDriverPath());
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                break;
            case IE:
                break;
            case PHANTOMJS:
                break;
            default:
                break;
        }

        String globalScopeVariableName = process.getVariableName();
        if (StringUtils.isEmpty(globalScopeVariableName)) {
            int count = 0;
            for (Map.Entry<String, Object> globalScopeVariableEntry : getGlobalScopeVariables().entrySet()) {
                if (globalScopeVariableEntry.getKey().startsWith(WEBDRIVER_PREFIX)) {
                    count++;
                }
            }
            globalScopeVariableName = WEBDRIVER_PREFIX + new DecimalFormat("000").format(count);
        }
        getGlobalScopeVariables().put(globalScopeVariableName, driver);
    }
}
