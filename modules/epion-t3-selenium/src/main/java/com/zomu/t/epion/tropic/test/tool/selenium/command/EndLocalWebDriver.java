package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.EndLocalWebDriverRunner;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "EndLocalWebDriver", runner = EndLocalWebDriverRunner.class)
public class EndLocalWebDriver extends Command {

    @NotEmpty
    private String refWebDriver;
}
