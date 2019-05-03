package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDSendKeysElementRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDSendKeysElement", runner = WDSendKeysElementRunner.class)
public class WDSendKeysElement extends AbstractWDCommand {
}
