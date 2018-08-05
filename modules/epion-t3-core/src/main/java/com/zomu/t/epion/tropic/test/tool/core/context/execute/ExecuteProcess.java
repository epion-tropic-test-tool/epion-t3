package com.zomu.t.epion.tropic.test.tool.core.context.execute;

import com.zomu.t.epion.tropic.test.tool.core.holder.ProcessLog;
import com.zomu.t.epion.tropic.test.tool.core.type.ProcessStatus;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class ExecuteProcess implements Serializable {

    /**
     * 実行プロセスID
     */
    private UUID executeProcessId = UUID.randomUUID();

    /**
     * 完全プロセス名称.
     * Full Query Process Name.
     */
    private String fqpn;

    /**
     * ステータス.
     */
    private ProcessStatus status = ProcessStatus.WAIT;

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
     * スタックトレース（エラー時のみ）.
     */
    private String stackTrace;

    /**
     * 対象プロセス.
     */
    @NonNull
    private Process process;

    /**
     * 拡張プロセスフィールド.
     */
    private Map<String, Object> extensionProcessFields;

    /**
     * ローカルスコープ変数.
     */
    private Map<String, Object> localVariables = new ConcurrentHashMap<>();

    /**
     * プロセスログリスト.
     */
    private List<ProcessLog> processLogs;

}
