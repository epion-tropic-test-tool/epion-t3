package com.zomu.t.epion.tropic.test.tool.core.context.execute;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioExecuteStatus;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Information;
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

/**
 * シナリオ実行時の情報保持クラス.
 *
 * @author takashno
 */
@Getter
@Setter
public class ExecuteScenario implements Serializable {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 実行フローID
     */
    private UUID executeScenarioId = UUID.randomUUID();

    /**
     * シナリオ情報.
     */
    private Information info;

    /**
     * 完全シナリオ名称.
     * Full Query Scenario Name.
     */
    private String fqsn;

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
     * 実行Flowリスト.
     */
    private List<ExecuteFlow> flows = new ArrayList<>();

    /**
     * シナリオスコープ変数.
     */
    Map<String, Object> scenarioVariables = new ConcurrentHashMap<>();

    /**
     * エビデンスマップ.
     */
    Map<String, EvidenceInfo> evidences = new ConcurrentHashMap<>();

    /**
     * フローIDとエビデンスIDの変換マップ.
     */
    Map<String, String> flowId2EvidenceId = new ConcurrentHashMap<>();

    /**
     * 実行結果ディレクトリパス.
     */
    private Path resultPath;

    /**
     * 実行結果-エビデンス格納パス.
     */
    private Path evidencePath;

}
