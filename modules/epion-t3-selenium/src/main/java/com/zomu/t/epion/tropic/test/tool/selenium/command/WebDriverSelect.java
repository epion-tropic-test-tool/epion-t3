package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverSelectRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@com.zomu.t.epion.tropic.test.tool.core.annotation.Command(id = "WebDriverSelect", runner = WebDriverSelectRunner.class)
public class WebDriverSelect extends Command {

    @NotEmpty
    private String refWebDriver;

    @NotEmpty
    private String selector;
}
