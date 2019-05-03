package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDSelectByValueElementsRunner;
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
