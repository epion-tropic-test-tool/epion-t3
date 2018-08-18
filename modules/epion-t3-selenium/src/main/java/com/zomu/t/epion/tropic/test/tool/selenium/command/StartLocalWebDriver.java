package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.StartLocalWebDriverRunner;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "StartLocalWebDriver", runner = StartLocalWebDriverRunner.class)
public class StartLocalWebDriver extends Command {

    @NotEmpty
    private String browser;

    @NotEmpty
    private String variableName;

    private Integer height;

    private Integer width;

    private String driverPath;


}
