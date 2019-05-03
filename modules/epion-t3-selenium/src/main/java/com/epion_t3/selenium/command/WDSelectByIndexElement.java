package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDSelectByIndexElementRunner;
import lombok.Getter;
import lombok.Setter;

/**
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "WDSelectByIndexElement", runner = WDSelectByIndexElementRunner.class)
public class WDSelectByIndexElement extends AbstractWDCommand {

    private Integer index;

}
