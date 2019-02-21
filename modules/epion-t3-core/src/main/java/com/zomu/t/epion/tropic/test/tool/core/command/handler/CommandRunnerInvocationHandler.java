package com.zomu.t.epion.tropic.test.tool.core.command.handler;

import com.zomu.t.epion.tropic.test.tool.core.command.handler.listener.CommandListenerFactory;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * コマンド実行時のプロキシクラス.
 * このプロキシクラスでは、以下のハンドリングを行う.
 *
 * @author takashno
 */
public class CommandRunnerInvocationHandler<COMMAND_RUNNER extends CommandRunner> implements InvocationHandler {

    private final COMMAND_RUNNER commandRunner;

    private final ExecuteContext executeContext;

    private final ExecuteScenario executeScenario;

    private final ExecuteFlow executeFlow;

    private final ExecuteCommand executeCommand;

    private static final String METHOD_REGISTRATION_OBJECT_EVIDENCE = "registrationObjectEvidence";

    private static final String METHOD_REGISTRATIONFILEEVIDENCE = "registrationFileEvidence";

    private static final String METHOD_REGISTRATION_OBJECT_EVIDENCE_WITH_NAME = "registrationObjectEvidenceWithName";

    private static final String METHOD_REGISTRATION_FILE_EVIDENCE_WITH_NAME = "registrationFileEvidenceWithName";

    /**
     * コンストラクタ.
     *
     * @param commandRunner
     */
    public CommandRunnerInvocationHandler(
            COMMAND_RUNNER commandRunner,
            ExecuteContext executeContext,
            ExecuteScenario executeScenario,
            ExecuteFlow executeFlow,
            ExecuteCommand executeCommand) {
        this.commandRunner = commandRunner;
        this.executeContext = executeContext;
        this.executeScenario = executeScenario;
        this.executeFlow = executeFlow;
        this.executeCommand = executeCommand;
    }

    /**
     * コマンド実行ハンドラ.
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 処理結果
        Object result = null;

        try {

            // コマンド前処理リスナーを実行
            CommandListenerFactory.getInstance().getBeforeListener().forEach(x -> x.beforeCommand(
                    commandRunner,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand
            ));


            // Before Handle
            beforeHandle(proxy, method, args);

            // 実行
            result = method.invoke(commandRunner, args);

            // After Handle
            afterHandle(proxy, method, args);

            // コマンド後処理リスナーを実行
            CommandListenerFactory.getInstance().getAfterListener().forEach(x -> x.afterCommand(
                    commandRunner,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand
            ));

        } catch (Exception e) {

            // コマンド後処理リスナーを実行
            CommandListenerFactory.getInstance().getErrorListener().forEach(x -> x.afterCommand(
                    commandRunner,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand,
                    e
            ));

        }

        return result;
    }

    /**
     * 処理前ハンドリング.
     *
     * @param proxy  対象オブジェクト
     * @param method メソッド
     * @param args   引数
     */
    private void beforeHandle(Object proxy, Method method, Object[] args) {
        // Do Nothing...
    }

    /**
     * 処理後ハンドリング.
     *
     * @param proxy  対象オブジェクト
     * @param method メソッド
     * @param args   引数
     */
    private void afterHandle(Object proxy, Method method, Object[] args) {
        // 実行対象のメソッド名を取得
        String methodName = method.getName();
        switch (methodName) {
            case METHOD_REGISTRATION_OBJECT_EVIDENCE:
            case METHOD_REGISTRATIONFILEEVIDENCE:
                // FlowIdとエビデンスIDの紐付け登録
                registrationFlowId2EvidenceId();
                break;
            case METHOD_REGISTRATION_FILE_EVIDENCE_WITH_NAME:
            case METHOD_REGISTRATION_OBJECT_EVIDENCE_WITH_NAME:
                // FlowIdとエビデンスIDの紐付け登録
                registrationFlowId2EvidenceId();
                registrationFlowId2EvidenceIdWithName((String) args[3]);
                break;
            default:
        }
    }


    /**
     * シナリオ実行中に最後に追加されたエビデンス情報と実行しているFlow情報を元にして、
     * FlowIdとエビデンスIDの紐付けマップに登録する.
     * なお、FlowIdは繰り返しFlow等で同一FlowIdが複数回シナリオの中で処理されることがあるため、
     * EvidenceIdはリスト形式で保持することとする.
     */
    private void registrationFlowId2EvidenceId() {
        Map.Entry<String, EvidenceInfo> entry =
                (Map.Entry<String, EvidenceInfo>) executeScenario.getEvidences().entrySet()
                        .toArray()[executeScenario.getEvidences().size() - 1];
        if (executeScenario.getFlowId2EvidenceId().containsKey(executeFlow.getFlow().getId())) {
            executeScenario.getFlowId2EvidenceId().get(executeFlow.getFlow().getId()).add(entry.getKey());
        } else {
            executeScenario.getFlowId2EvidenceId().put(executeFlow.getFlow().getId(), new ArrayList<>());
        }
    }

    /**
     * シナリオ実行中に最後に追加されたエビデンス情報と実行しているFlow情報を元にして、
     * 指定された名称とエビデンスIDの紐付けマップに登録する.
     * なお、名称は繰り返しFlow等で同一名称が複数回シナリオの中で処理されることがあるため、
     * EvidenceIdはリスト形式で保持することとする.
     *
     * @param name 名称
     */
    private void registrationFlowId2EvidenceIdWithName(String name) {
        Map.Entry<String, EvidenceInfo> entry =
                (Map.Entry<String, EvidenceInfo>) executeScenario.getEvidences().entrySet()
                        .toArray()[executeScenario.getEvidences().size() - 1];
        if (executeScenario.getFlowId2EvidenceId().containsKey(name)) {
            executeScenario.getFlowId2EvidenceId().get(name).add(entry.getKey());
        } else {
            executeScenario.getFlowId2EvidenceId().put(name, new ArrayList<>());
            executeScenario.getFlowId2EvidenceId().get(name).add(entry.getKey());
        }
    }

}
