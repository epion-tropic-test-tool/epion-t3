package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDSendKeysElementRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDSendKeysElement", runner = WDSendKeysElementRunner.class)
public class WDSendKeysElement extends AbstractWDCommand {
}
