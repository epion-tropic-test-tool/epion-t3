package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDSelectVisibleTextElementRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDSelectVisibleTextElements", runner = WDSelectVisibleTextElementRunner.class)
public class WDSelectVisibleTextElements extends AbstractWDCommand {
    private Integer elementIndex;
}
