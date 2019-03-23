package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDSendKeysElementsRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "WDSendKeysElements", runner = WDSendKeysElementsRunner.class)
public class WDSendKeysElements extends Command {

    @NotEmpty
    private String refWebDriver;

    @NotEmpty
    private String selector;

    private Integer elementIndex;
}
