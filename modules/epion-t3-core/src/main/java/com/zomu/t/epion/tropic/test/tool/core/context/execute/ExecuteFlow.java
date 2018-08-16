package com.zomu.t.epion.tropic.test.tool.core.context.execute;

import com.zomu.t.epion.tropic.test.tool.core.holder.FlowLog;
import com.zomu.t.epion.tropic.test.tool.core.type.FlowStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Flow実行情報保持クラス.
 *
 * @author takashno
 */
@Getter
@Setter
public class ExecuteFlow extends ExecuteElement {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * ステータス.
     */
    private FlowStatus status = FlowStatus.WAIT;

    /**
     * 実行Flowリスト.
     */
    private final List<ExecuteCommand> commands = new ArrayList<>();


    /**
     * Flowスコープ変数.
     */
    private final Map<String, Object> flowVariables = new ConcurrentHashMap<>();

    /**
     * Flowログ.
     */
    private List<FlowLog> flowLogs;

}
