package com.zomu.t.epion.tropic.test.tool.core.flow.model;

import com.zomu.t.epion.tropic.test.tool.core.type.FlowResultStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author takashno
 */
@Getter
@Setter
public class FlowResult implements Serializable {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    private FlowResultStatus status = FlowResultStatus.NEXT;

    private String choiceId;

    public static FlowResult getDefault() {
        return new FlowResult();
    }

}
