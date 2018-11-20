package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverContainsUrlTextRunner;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WebDriverSendKeysRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "WebDriverContainsUrlText", runner = WebDriverContainsUrlTextRunner.class)
public class WebDriverContainsUrlText extends Command {

    @NotEmpty
    private String refWebDriver;

}
