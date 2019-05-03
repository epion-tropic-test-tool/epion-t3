package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDSelectByIndexElementsRunner;
import lombok.Getter;
import lombok.Setter;

/**
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "WDSelectByIndexElements", runner = WDSelectByIndexElementsRunner.class)
public class WDSelectByIndexElements extends AbstractWDCommand {

    private Integer index;

    private Integer elementIndex;
}
