package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDContainsUrlTextRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "WDContainsUrlText", runner = WDContainsUrlTextRunner.class)
public class WDContainsUrlText extends Command {

    @NotEmpty
    private String refWebDriver;

}
