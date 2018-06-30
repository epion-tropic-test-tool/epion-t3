package com.zomu.t.t3.core.type;

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

    WAIT,

    RUNNING,

    SUUCESS,

    FAIL;

}
