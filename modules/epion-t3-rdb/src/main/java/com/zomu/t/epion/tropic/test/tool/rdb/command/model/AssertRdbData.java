package com.zomu.t.epion.tropic.test.tool.rdb.command.model;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rdb.bean.TargetTable;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
public class AssertRdbData extends Command {

    @NotEmpty
    private String expected;

    @NotEmpty
    private String actual;

    /**
     * データセット種別.
     */
    @NotEmpty
    private String actualDataSetType = "excel";

    /**
     * テーブル指定.
     */
    private List<TargetTable> tables;

}
