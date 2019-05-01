package com.zomu.t.epion.tropic.test.tool.rest.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rest.runner.AssertHttpHeaderRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * HTTPステータス確認コマンド.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "AssertHttpHeader",
        runner = AssertHttpHeaderRunner.class,
        assertCommand = true)
public class AssertHttpHeader extends Command {

    @NotEmpty
    private String header;
}
