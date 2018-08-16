package com.zomu.t.epion.tropic.test.tool.core.execution.resolver;

import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;

/**
 * コマンド実行インタフェース.
 *
 * @author takashno
 */
public interface CommandRunnerResolver {

    CommandRunner getCommandRunner(String commandId);

}
