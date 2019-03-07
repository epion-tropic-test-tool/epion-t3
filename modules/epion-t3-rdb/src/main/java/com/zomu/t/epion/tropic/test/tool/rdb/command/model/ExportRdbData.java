package com.zomu.t.epion.tropic.test.tool.rdb.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rdb.command.runner.ImportRdbDataRunner;
import org.apache.bval.constraints.NotEmpty;

/**
 * @author takashno
 */
@CommandDefinition(id = "ImportRdbData", runner = ImportRdbDataRunner.class)
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
}
