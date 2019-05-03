package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDClearSendKeysElementRunner;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
@CommandDefinition(id = "WDClearSendKeysElement", runner = WDClearSendKeysElementRunner.class)
public class WDClearSendKeysElement extends AbstractWDCommand {
}
