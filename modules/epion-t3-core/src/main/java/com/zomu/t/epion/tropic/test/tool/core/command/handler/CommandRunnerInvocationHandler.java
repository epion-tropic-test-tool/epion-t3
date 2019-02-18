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
     * @param executeContext
     * @param executeScenario
     * @param executeFlow
     * @param executeCommand
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

            String methodName = method.getName();

            // Before Handle

            result = method.invoke(commandRunner, args);

            // After Handle

            switch (methodName) {
                case METHOD_REGISTRATION_OBJECT_EVIDENCE:
                case METHOD_REGISTRATIONFILEEVIDENCE:
                    Map.Entry<String, EvidenceInfo> entry =
                            (Map.Entry<String, EvidenceInfo>) executeScenario.getEvidences().entrySet()
                                    .toArray()[executeScenario.getEvidences().size() - 1];
                    executeScenario.getFlowId2EvidenceId().put(executeFlow.getFlow().getId(), entry.getKey());
                    break;
                case METHOD_REGISTRATION_FILE_EVIDENCE_WITH_NAME:
                case METHOD_REGISTRATION_OBJECT_EVIDENCE_WITH_NAME:
                    break;
                default:
            }

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

}
