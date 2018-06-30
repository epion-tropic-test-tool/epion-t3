package com.zomu.t.t3.core.model.context;

import com.zomu.t.t3.v10.model.context.execute.ExecuteScenario;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 */
public interface ExecuteContext extends Serializable {

    /**
     * @return
     */
    UUID getExecuteContextId();

    /**
     * @return
     */
    List<ExecuteScenario> getScenarios();

    /**
     * @return
     */
    Map<String, Object> getGlobalVariables();

}
