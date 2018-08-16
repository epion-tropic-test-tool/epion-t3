package com.zomu.t.epion.tropic.test.tool.core.flow.model;

import com.zomu.t.epion.tropic.test.tool.core.flow.runner.impl.CommandExecuteFlowRunner;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Flow;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * コマンド実行を行うためのFlow定義.
 */
@Getter
@Setter
@com.zomu.t.epion.tropic.test.tool.core.annotation.Flow(
        id = "CommandExecute", runner = CommandExecuteFlowRunner.class)
public class CommandExecuteFlow extends Flow {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    @NotEmpty
    String ref;

}
