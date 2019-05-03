package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDSendKeysSpaceElementRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDSendKeysSpaceElement", runner = WDSendKeysSpaceElementRunner.class)
public class WDSendKeysSpaceElement extends AbstractWDCommand {
}
