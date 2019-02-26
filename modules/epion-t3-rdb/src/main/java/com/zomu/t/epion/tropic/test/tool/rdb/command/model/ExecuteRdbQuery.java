package com.zomu.t.epion.tropic.test.tool.rdb.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rdb.command.runner.ExecuteRdbQueryRunner;
import lombok.Getter;
import lombok.Setter;

/**
 * RDBに対するクエリーを実行するコマンド処理.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "ExecuteRdbQuery", runner = ExecuteRdbQueryRunner.class)
public class ExecuteRdbQuery extends Command {
}
