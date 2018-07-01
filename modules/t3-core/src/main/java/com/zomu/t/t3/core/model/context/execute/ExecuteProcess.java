package com.zomu.t.t3.core.model.context.execute;

import com.zomu.t.t3.core.type.ProcessStatus;
import com.zomu.t.t3.model.scenario.Process;
import lombok.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
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
     * 対象プロセス.
     */
    @NonNull
    private Process process;

    /**
     * ローカルスコープ変数.
     */
    Map<String, Object> localVariables = new ConcurrentHashMap<>();


}
