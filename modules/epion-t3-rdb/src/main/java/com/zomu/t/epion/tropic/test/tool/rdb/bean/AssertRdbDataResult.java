package com.zomu.t.epion.tropic.test.tool.rdb.bean;

import com.zomu.t.epion.tropic.test.tool.core.command.model.AssertCommandResult;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * アサートRDBData結果.
 *
 * @author takashno
 */
@Getter
@Setter
public class AssertRdbDataResult extends AssertCommandResult {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * アサート結果テーブル.
     */
    private List<AssertResultTable> tables = new ArrayList<>();

}
