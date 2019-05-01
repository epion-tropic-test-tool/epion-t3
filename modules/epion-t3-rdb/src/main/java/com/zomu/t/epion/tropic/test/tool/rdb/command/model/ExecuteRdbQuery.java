package com.zomu.t.epion.tropic.test.tool.rdb.command.model;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rdb.command.runner.ExecuteRdbQueryRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * RDBに対するクエリーを実行するコマンド処理.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "ExecuteRdbQuery", runner = ExecuteRdbQueryRunner.class)
public class ExecuteRdbQuery extends Command {

    /**
     * RDB接続設定参照.
     */
    @NotEmpty
    private String rdbConnectConfigRef;

}
