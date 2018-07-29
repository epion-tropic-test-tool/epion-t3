package com.zomu.t.epion.tropic.test.tool.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * シナリオ実行ステータス.
 *
 * @author takashno
 */
@Getter
@AllArgsConstructor
public enum ScenarioExecuteStatus {

    WAIT("table-secondary"),

    RUNNING("table-info"),

    SUUCESS("table-success"),

    FAIL("table-danger");

    private String cssClass;

}
