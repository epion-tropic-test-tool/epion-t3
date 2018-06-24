package com.zomu.t.t3.v10.model.execute;

import com.zomu.t.t3.core.type.FlowStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class ExecuteFlow implements Serializable {

    /**
     * ステータス.
     */
    private FlowStatus status = FlowStatus.WAIT;

    /**
     * 開始日時.
     */
    private LocalDateTime start;

    /**
     * 終了日時.
     */
    private LocalDateTime end;

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

}
