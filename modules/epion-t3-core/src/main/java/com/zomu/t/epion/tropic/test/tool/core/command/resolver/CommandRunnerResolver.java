package com.zomu.t.epion.tropic.test.tool.core.command.resolver;

import com.zomu.t.epion.tropic.test.tool.core.command.handler.CommandRunnerInvocationHandler;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;

/**
 * コマンド実行クラスの解決処理インタフェース.
 *
 * @author takashno
 */
public interface CommandRunnerResolver {

    /**
     * コマンド実行処理を取得する.
     * コマンド実行処理はProxyによって任意の拡張リスナー処理を実装できるようにする.
     * そのため、InvocationHandlerにてラップする形で提供を行うこと.
     *
     * @param commandId コマンドID
     * @return コマンド実行処理
     */
    CommandRunnerInvocationHandler getCommandRunner(String commandId);

}
