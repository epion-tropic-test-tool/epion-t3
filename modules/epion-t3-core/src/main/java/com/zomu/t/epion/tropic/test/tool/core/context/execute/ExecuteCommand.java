package com.zomu.t.epion.tropic.test.tool.core.context.execute;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.holder.CommandLog;
import com.zomu.t.epion.tropic.test.tool.core.type.CommandStatus;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExecuteCommand extends ExecuteElement {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * コマンド結果.
     */
    private CommandResult commandResult;

    /**
     * 対象コマンド.
     */
    private Command command;

    /**
     * 拡張プロセスフィールド.
     */
    private Map<String, Object> extensionProcessFields;


    /**
     * プロセスログリスト.
     */
    private List<CommandLog> commandLogs;

}
