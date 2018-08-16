package com.zomu.t.epion.tropic.test.tool.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Flowステータス.
 *
 * @author takashno
 */
@Getter
@AllArgsConstructor
public enum FlowStatus {

    WAIT("table-secondary"),

    RUNNING("table-info"),

    SUCCESS("table-success"),

    FAIL("table-danger");

    private String cssClass;

}
