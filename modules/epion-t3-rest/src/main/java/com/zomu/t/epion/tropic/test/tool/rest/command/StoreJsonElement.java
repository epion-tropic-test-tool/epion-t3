package com.zomu.t.epion.tropic.test.tool.rest.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rest.runner.StoreJsonElementRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * JSONボディ要素ストア.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "StoreJsonElement", runner = StoreJsonElementRunner.class)
public class StoreJsonElement extends Command {

    /**
     * JSONPath.
     */
    @NotEmpty
    private String jsonPath;
}
