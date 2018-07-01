package com.zomu.t.t3.core.model.context.execute;

import com.zomu.t.t3.core.type.ScenarioExecuteStatus;
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
public class ExecuteContext implements com.zomu.t.t3.core.model.context.ExecuteContext {

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
