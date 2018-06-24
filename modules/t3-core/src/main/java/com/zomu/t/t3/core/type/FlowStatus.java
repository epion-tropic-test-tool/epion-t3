package com.zomu.t.t3.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * フローステータス.
 *
 * @author takashno
 */
@Getter
@AllArgsConstructor
public enum FlowStatus {

    WAIT,

    RUNNING,

    SUUCESS,

    FAIL;

}
