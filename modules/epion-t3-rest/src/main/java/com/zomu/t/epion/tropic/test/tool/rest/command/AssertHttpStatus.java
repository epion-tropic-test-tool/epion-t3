package com.zomu.t.epion.tropic.test.tool.rest.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rest.runner.AssertHttpStatusRunner;
import lombok.Getter;
import lombok.Setter;

/**
 * HTTPステータス確認コマンド.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "AssertHttpStatus",
        runner = AssertHttpStatusRunner.class,
        assertCommand = true)
public class AssertHttpStatus extends Command {
}
