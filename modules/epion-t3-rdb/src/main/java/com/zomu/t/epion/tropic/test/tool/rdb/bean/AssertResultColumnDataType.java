package com.zomu.t.epion.tropic.test.tool.rdb.bean;

import com.zomu.t.epion.tropic.test.tool.core.type.AssertStatus;
import lombok.Getter;
import lombok.Setter;
import org.dbunit.dataset.datatype.DataType;

import java.io.Serializable;

/**
 * アサート結果カラム値.
 *
 * @author takashno
 */
@Getter
@Setter
public class AssertResultColumnDataType implements Serializable {

    /**
     * シリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 期待値.
     */
    private DataType expected;

    /**
     * 結果値.
     */
    private DataType actual;

    /**
     * アサート結果.
     */
    private AssertStatus status;

}
