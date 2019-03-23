package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDSendKeysSpaceElementRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDSendKeysSpaceElement", runner = WDSendKeysSpaceElementRunner.class)
public class WDSendKeysSpaceElement extends WDCommand {
}
