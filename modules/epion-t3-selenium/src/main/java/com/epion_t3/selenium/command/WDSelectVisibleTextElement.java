package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDSelectVisibleTextElementRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "WDSelectVisibleTextElement", runner = WDSelectVisibleTextElementRunner.class)
public class WDSelectVisibleTextElement extends AbstractWDCommand {
}
