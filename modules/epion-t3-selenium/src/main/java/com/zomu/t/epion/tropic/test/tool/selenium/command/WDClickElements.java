package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDClickElementsRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDClickElements", runner = WDClickElementsRunner.class)
public class WDClickElements extends AbstractWDCommand {
    private Integer elementIndex;
}
