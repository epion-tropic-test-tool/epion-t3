package com.zomu.t.epion.tropic.test.tool.rdb.bean;

import com.zomu.t.epion.tropic.test.tool.core.common.type.AssertStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * アサート結果カラム値.
 *
 * @author takashno
 */
@Getter
@Setter
public class AssertResultColumnValue implements Serializable {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * カラム名.
     */
    private String name;

    /**
     * 期待値.
     */
    private Object expected;

    /**
     * 結果値.
     */
    private Object actual;

    /**
     * 無視カラム.
     */
    private boolean ignore = false;

    /**
     * アサート結果.
     */
    private AssertStatus status;
}
