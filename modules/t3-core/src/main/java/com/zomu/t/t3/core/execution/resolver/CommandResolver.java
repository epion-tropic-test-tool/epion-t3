package com.zomu.t.t3.core.execution.resolver;

import com.zomu.t.t3.core.execution.runner.CommandRunner;

/**
 * コマンド実行インタフェース.
 *
 * @author takashno
 */
public interface CommandResolver {

    CommandRunner getCommandRunner(String commandId);

}
