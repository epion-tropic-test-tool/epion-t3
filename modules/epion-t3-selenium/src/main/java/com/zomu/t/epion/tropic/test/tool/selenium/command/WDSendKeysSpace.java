package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDSendKeysSpaceRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "WDSendKeysSpace", runner = WDSendKeysSpaceRunner.class)
public class WDSendKeysSpace extends Command {

    @NotEmpty
    private String refWebDriver;

    @NotEmpty
    private String selector;
}
