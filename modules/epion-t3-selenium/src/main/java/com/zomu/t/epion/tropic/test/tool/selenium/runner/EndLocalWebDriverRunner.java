package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.selenium.command.EndLocalWebDriver;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.util.Map;

public class EndLocalWebDriverRunner extends AbstractCommandRunner<EndLocalWebDriver> {

    @Override
    public void execute(
            final EndLocalWebDriver process,
            Logger logger) throws Exception {

        WebDriver driver = WebDriver.class.cast(getGlobalScopeVariables().get(process.getRefWebDriver()));
        //driver.close();
        //driver.quit();
    }
}
