package com.zomu.t.epion.tropic.test.tool.core.context;

import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;

import java.io.Serializable;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
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
     * @param resultRootPath
     */
    void setResultRootPath(Path resultRootPath);

    /**
     * @return
     */
    Path getResultRootPath();

    /**
     * @return
     */
    List<ExecuteScenario> getScenarios();

    /**
     * @return
     */
    Map<String, Object> getGlobalVariables();

    LocalDateTime getStart();

    LocalDateTime getEnd();

    Duration getDuration();

}
