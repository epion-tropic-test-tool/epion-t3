package com.zomu.t.epion.tropic.test.tool.core.command.resolver;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;

/**
 * コマンド実行クラスの解決処理インタフェース.
 *
 * @author takashno
 */
public interface CommandRunnerResolver {

    /**
     * コマンド実行処理を取得する.
     *
     * @param commandId
     * @return
     */
    CommandRunner getCommandRunner(String commandId);

}
