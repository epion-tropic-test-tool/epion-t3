package com.zomu.t.t3.v10.model.execute;

import com.zomu.t.t3.core.type.FlowStatus;
import com.zomu.t.t3.core.type.ProcessStatus;
import com.zomu.t.t3.v10.model.scenario.Process;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteProcess implements Serializable {

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
     * エラー.
     */
    private Throwable error;

    /**
     * 対象プロセス.
     */
    @NonNull
    private Process process;

}
