package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDScreenShotRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "WDScreenShot", runner = WDScreenShotRunner.class)
public class WDScreenShot extends Command {

    @NotEmpty
    private String refWebDriver;
}
