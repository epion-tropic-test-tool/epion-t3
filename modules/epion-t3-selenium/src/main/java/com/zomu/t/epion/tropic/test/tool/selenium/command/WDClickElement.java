package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDClickElementRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDClickElement", runner = WDClickElementRunner.class)
public class WDClickElement extends AbstractWDCommand {
}
