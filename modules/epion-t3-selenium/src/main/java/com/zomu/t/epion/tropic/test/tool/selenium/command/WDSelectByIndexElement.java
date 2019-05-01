package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.WDSelectByIndexElementRunner;
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
