package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.selenium.runner.StartLocalWebDriverRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@Command(id = "StartLocalWebDriver", runner = StartLocalWebDriverRunner.class)
public class StartLocalWebDriver extends Process {

    @NotEmpty
    private String browser;

    @NotEmpty
    private String variableName;

    private Integer height;

    private Integer width;

    private String driverPath;


}
