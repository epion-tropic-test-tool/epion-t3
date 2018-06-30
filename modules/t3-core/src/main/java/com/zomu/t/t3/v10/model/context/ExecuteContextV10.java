package com.zomu.t.t3.v10.model.context;

import com.zomu.t.t3.core.model.context.ExecuteContext;
import com.zomu.t.t3.core.type.ScenarioExecuteStatus;
import com.zomu.t.t3.v10.model.context.execute.ExecuteScenario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class ExecuteContextV10 implements ExecuteContext {

    /**
     * 実行ID
     */
    private UUID executeContextId = UUID.randomUUID();

    /**
     * ステータス.
     */
    private ScenarioExecuteStatus status = ScenarioExecuteStatus.WAIT;

    /**
     * 開始日時.
     */
    private LocalDateTime start;

    /**
     * 終了日時.
     */
    private LocalDateTime end;


    /**
     * 実行するフローリスト.
     */
    List<ExecuteScenario> scenarios = new ArrayList<>();

    /**
     * Globalスコープ
     */
    Map<String, Object> globalVariables = new ConcurrentHashMap<>();

}
