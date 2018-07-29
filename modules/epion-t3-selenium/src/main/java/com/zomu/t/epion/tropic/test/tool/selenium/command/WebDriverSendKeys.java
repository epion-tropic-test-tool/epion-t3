package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverSendKeysRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@Command(id = "WebDriverSendKeys", runner = WebDriverSendKeysRunner.class)
public class WebDriverSendKeys extends Process {

    @NotEmpty
    private String refWebDriver;

    @NotEmpty
    private String selector;
}
