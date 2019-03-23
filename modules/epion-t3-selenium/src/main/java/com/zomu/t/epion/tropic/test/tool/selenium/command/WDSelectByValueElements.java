package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDSelectByValueElementsRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "WDSelectByValueElements", runner = WDSelectByValueElementsRunner.class)
public class WDSelectByValueElements extends Command {

    @NotEmpty
    private String refWebDriver;

    @NotEmpty
    private String selector;

    private Integer elementIndex;
    
}
