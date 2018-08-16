package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverScreenShotRunner;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@com.zomu.t.epion.tropic.test.tool.core.annotation.Command(id = "WebDriverScreenShot", runner = WebDriverScreenShotRunner.class)
public class WebDriverScreenShot extends Command {

    @NotEmpty
    private String refWebDriver;
}
