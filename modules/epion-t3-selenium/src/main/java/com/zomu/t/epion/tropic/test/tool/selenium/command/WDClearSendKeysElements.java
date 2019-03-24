package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDClearSendKeysElementsRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDClearSendKeysElements", runner = WDClearSendKeysElementsRunner.class)
public class WDClearSendKeysElements extends AbstractWDCommand {
    private Integer elementIndex;
}
