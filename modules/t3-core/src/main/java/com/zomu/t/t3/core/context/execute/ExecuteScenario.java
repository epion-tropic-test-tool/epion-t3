package com.zomu.t.t3.core.context.execute;

import com.zomu.t.t3.core.type.ScenarioExecuteStatus;
import com.zomu.t.t3.model.scenario.Information;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class ExecuteScenario implements Serializable {

    /**
     * 実行フローID
     */
    private UUID executeScenarioId = UUID.randomUUID();

    /**
     * シナリオ情報.
     */
    private Information info;

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
     * 所要時間.
     */
    private Duration duration;

    /**
     * エラー.
     */
    private Throwable error;

    /**
     * 対象プロセス.
     */
    private List<ExecuteProcess> processes = new ArrayList<>();

    /**
     * シナリオスコープ変数.
     */
    Map<String, Object> scenarioVariables = new ConcurrentHashMap<>();

    /**
     * 実行結果ディレクトリパス.
     */
    private Path resultPath;

}
