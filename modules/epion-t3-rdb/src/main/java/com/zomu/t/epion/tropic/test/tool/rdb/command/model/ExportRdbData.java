package com.zomu.t.epion.tropic.test.tool.rdb.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rdb.command.runner.ImportRdbDataRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import java.util.List;

/**
 * RDBデータのエクスポートコマンド.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "ExportRdbData", runner = ImportRdbDataRunner.class)
public class ExportRdbData extends Command {

    /**
     * RDB接続設定参照.
     */
    @NotEmpty
    private String rdbConnectConfigRef;

    /**
     * データセット種別.
     */
    @NotEmpty
    private String dataSetType = "excel";

    /**
     * テーブル指定.
     */
    private List<TargetTable> targets;

}
