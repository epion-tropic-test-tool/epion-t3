package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverClickRunner;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverSendKeysRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@Command(id = "WebDriverClick", runner = WebDriverClickRunner.class)
public class WebDriverClick extends Process {

    @NotEmpty
    private String refWebDriver;

    @NotEmpty
    private String selector;
}
