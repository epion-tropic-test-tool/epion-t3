package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverScreenShotRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@Command(id = "WebDriverScreenShot", runner = WebDriverScreenShotRunner.class)
public class WebDriverScreenShot extends Process {

    @NotEmpty
    private String refWebDriver;
}
