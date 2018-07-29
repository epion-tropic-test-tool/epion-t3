package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverScreenShotRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Command(id = "WebDriverScreenShot", runner = WebDriverScreenShotRunner.class)
public class WebDriverScreenShot extends Process {

    private String refWebDriver;
}
