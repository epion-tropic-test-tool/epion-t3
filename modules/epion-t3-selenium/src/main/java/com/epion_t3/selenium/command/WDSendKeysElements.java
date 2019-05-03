package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDSendKeysElementsRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDSendKeysElements", runner = WDSendKeysElementsRunner.class)
public class WDSendKeysElements extends AbstractWDCommand {
    private Integer elementIndex;
}
