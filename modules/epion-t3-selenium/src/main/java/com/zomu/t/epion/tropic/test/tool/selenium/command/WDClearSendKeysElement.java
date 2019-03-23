package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDClearSendKeysElementRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDClearSendKeysElement", runner = WDClearSendKeysElementRunner.class)
public class WDClearSendKeysElement extends WDCommand {
}
