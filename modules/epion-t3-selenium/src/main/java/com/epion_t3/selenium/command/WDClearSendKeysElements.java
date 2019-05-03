package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDClearSendKeysElementsRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDClearSendKeysElements", runner = WDClearSendKeysElementsRunner.class)
public class WDClearSendKeysElements extends AbstractWDCommand {
    private Integer elementIndex;
}
