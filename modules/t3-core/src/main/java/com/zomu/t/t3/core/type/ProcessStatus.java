package com.zomu.t.t3.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 処理ステータス.
 *
 * @author takashno
 */
@Getter
@AllArgsConstructor
public enum ProcessStatus {

    WAIT,

    RUNNING,

    SUUCESS,

    FAIL;

}
