package com.zomu.t.epion.tropic.test.tool.rdb.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rdb.command.runner.ExecuteRdbQueryRunner;
import com.zomu.t.epion.tropic.test.tool.rdb.command.runner.ExecuteRdbScriptRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * RDBに対するスクリプト（SQLファイル）を実行するコマンド処理.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "ExecuteRdbScript", runner = ExecuteRdbScriptRunner.class)
public class ExecuteRdbScript extends Command {

    /**
     * RDB接続設定参照.
     */
    @NotEmpty
    private String rdbConnectConfigRef;

}
