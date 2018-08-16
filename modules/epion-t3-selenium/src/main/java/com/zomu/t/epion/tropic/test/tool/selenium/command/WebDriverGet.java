package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverGetRunner;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@com.zomu.t.epion.tropic.test.tool.core.annotation.Command(id = "WebDriverGet", runner = WebDriverGetRunner.class)
public class WebDriverGet extends Command {

    private String refWebDriver;
}
