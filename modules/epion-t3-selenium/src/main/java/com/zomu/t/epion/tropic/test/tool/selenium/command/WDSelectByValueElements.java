package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDSelectByValueElementsRunner;
import lombok.Getter;
import lombok.Setter;

/**
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "WDSelectByValueElements", runner = WDSelectByValueElementsRunner.class)
public class WDSelectByValueElements extends AbstractWDCommand {

    private Integer elementIndex;
    
}
