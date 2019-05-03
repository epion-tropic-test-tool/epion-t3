package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDClickElementsRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDClickElements", runner = WDClickElementsRunner.class)
public class WDClickElements extends AbstractWDCommand {
    private Integer elementIndex;
}
