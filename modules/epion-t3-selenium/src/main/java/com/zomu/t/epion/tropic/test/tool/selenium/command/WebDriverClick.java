package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverClickRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@com.zomu.t.epion.tropic.test.tool.core.annotation.Command(id = "WebDriverClick", runner = WebDriverClickRunner.class)
public class WebDriverClick extends Command {

    @NotEmpty
    private String refWebDriver;

    @NotEmpty
    private String selector;
}
