package com.zomu.t.epion.tropic.test.tool.core.context.execute;

import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioExecuteStatus;
import com.zomu.t.epion.tropic.test.tool.core.type.ExitCode;
import lombok.Getter;
import lombok.Setter;

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
public class ExecuteContext implements com.zomu.t.epion.tropic.test.tool.core.context.ExecuteContext {

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
     * 所要時間.
     */
    private Duration duration;

    /**
     * 実行したシナリオリスト.
     */
    private List<ExecuteScenario> scenarios = new ArrayList<>();

    /**
     * Globalスコープ.
     */
    private Map<String, Object> globalVariables = new ConcurrentHashMap<>();


    /**
     * 実行結果ディレクトリパス.
     */
    private Path resultRootPath;

    /**
     * 終了コード.
     */
    private ExitCode exitCode = ExitCode.UNASSIGNED;

}
