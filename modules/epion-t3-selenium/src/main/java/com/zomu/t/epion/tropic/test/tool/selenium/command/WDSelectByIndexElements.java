package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDSelectByIndexElementsRunner;
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
