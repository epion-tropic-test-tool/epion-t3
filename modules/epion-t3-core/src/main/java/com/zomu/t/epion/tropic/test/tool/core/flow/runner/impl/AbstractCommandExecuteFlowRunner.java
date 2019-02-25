package com.zomu.t.epion.tropic.test.tool.core.flow.runner.impl;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.exception.CommandNotFoundException;
import com.zomu.t.epion.tropic.test.tool.core.command.resolver.impl.CommandRunnerResolverImpl;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.flow.model.CommandExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.holder.CommandLog;
import com.zomu.t.epion.tropic.test.tool.core.holder.CommandLoggingHolder;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Flow;
import com.zomu.t.epion.tropic.test.tool.core.type.CommandStatus;
import com.zomu.t.epion.tropic.test.tool.core.type.FlowScopeVariables;
import com.zomu.t.epion.tropic.test.tool.core.type.FlowStatus;
import com.zomu.t.epion.tropic.test.tool.core.util.BindUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * コマンド実行のフロー実行処理の基底クラス.
 *
 * @param <EXECUTE_SCENARIO>
 * @param <EXECUTE_COMMAND>
 * @param <FLOW>
 * @author takashno
 */
@Slf4j
public abstract class AbstractCommandExecuteFlowRunner<
        EXECUTE_CONTEXT extends ExecuteContext,
        EXECUTE_SCENARIO extends ExecuteScenario,
        EXECUTE_FLOW extends ExecuteFlow,
        EXECUTE_COMMAND extends ExecuteCommand,
        FLOW extends Flow,
        ELEMENT_FLOW extends CommandExecuteFlow>
        extends AbstractFlowRunner<EXECUTE_CONTEXT, EXECUTE_SCENARIO, EXECUTE_FLOW, FLOW> {

    /**
     * {@inheritDoc}
     */
    protected void executeCommand(
            final Context context,
            final EXECUTE_CONTEXT executeContext,
            final EXECUTE_SCENARIO executeScenario,
            final EXECUTE_FLOW executeFlow,
            final ELEMENT_FLOW flow,
            final Logger logger) {


        Command command = context.getOriginal().getProcesses().get(flow.getRef());
        if (command == null) {
            command = context.getOriginal().getProcesses().get(executeScenario.getFqsn() + "." + flow.getRef());
        }

        if (command == null) {
            log.error("not found command: {}", flow.getRef());
            throw new CommandNotFoundException(flow.getRef());
        }

        // プロセス実行情報を生成
        EXECUTE_COMMAND executeCommand = getExecuteCommandInstance();
        executeFlow.getCommands().add(executeCommand);
        executeCommand.setCommand(command);

        // シナリオ実行開始時間を設定
        executeCommand.setStart(LocalDateTime.now());

        try {

            // プロセス開始ログ出力
            outputStartProcessLog(
                    context,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand);

            // シナリオスコープ変数の設定
            settingFlowVariables(
                    context,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand);

            // コマンド解決
            String commandId = executeCommand.getCommand().getCommand();

            // コマンド実行クラスを解決
            CommandRunner runner = CommandRunnerResolverImpl.getInstance().getCommandRunner(
                    commandId,
                    context,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand);

            // 変数バインド
            bind(
                    context,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand);

            // コマンド実行
            runner.execute(executeCommand.getCommand(),
                    context,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand,
                    LoggerFactory.getLogger("ProcessLog"));

            // プロセス成功
            executeCommand.getCommandResult().setStatus(CommandStatus.SUCCESS);

            // Flow成功
            executeFlow.setStatus(FlowStatus.SUCCESS);

        } catch (Throwable t) {

            log.debug("error occurred...", t);

            // 発生したエラーを設定
            executeCommand.setError(t);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.flush();
            executeCommand.setStackTrace(sw.toString());

            // プロセス失敗
            executeCommand.getCommandResult().setStatus(CommandStatus.FAIL);

        } finally {

            // 掃除
            cleanFlowVariables(
                    context,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand);

            // シナリオ実行終了時間を設定
            executeCommand.setEnd(LocalDateTime.now());

            // 所用時間を設定
            executeCommand.setDuration(Duration.between(executeCommand.getStart(), executeCommand.getEnd()));

            // プロセス終了ログ出力
            outputEndProcessLog(
                    context,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand);

            // プロセスのログを収集
            List<CommandLog> commandLogs = SerializationUtils.clone(CommandLoggingHolder.get());
            executeCommand.setCommandLogs(commandLogs);

            // プロセスのログは収集し終えたらクリアする（ThreadLocalにて保持）
            CommandLoggingHolder.clear();

        }

    }

    /**
     * 実行するコマンドのインスタンスを取得する.
     *
     * @return
     */
    private EXECUTE_COMMAND getExecuteCommandInstance() {
        try {
            Class<?> clazz = this.getClass();
            Type type = clazz.getGenericSuperclass();
            ParameterizedType pt = (ParameterizedType) type;
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            Class<?> entityClass = (Class<?>) actualTypeArguments[3];
            return (EXECUTE_COMMAND) entityClass.newInstance();
        } catch (ReflectiveOperationException e) {
            // TODO:ErrorProcess
            throw new RuntimeException(e);
        }
    }

    /**
     * コマンドに対して、変数をバインドする.
     *
     * @param context
     * @param executeScenario
     * @param executeCommand
     */
    private void bind(final Context context,
                      final EXECUTE_CONTEXT executeContext,
                      final EXECUTE_SCENARIO executeScenario,
                      final EXECUTE_FLOW executeFlow,
                      final EXECUTE_COMMAND executeCommand) {

        final Map<String, String> profiles = new ConcurrentHashMap<>();

        if (StringUtils.isNotEmpty(context.getOption().getProfile())) {
            // プロファイルを抽出
            Arrays.stream(context.getOption().getProfile().split(","))
                    .forEach(x -> {
                        if (context.getOriginal().getProfiles().containsKey(x)) {
                            profiles.putAll(context.getOriginal().getProfiles().get(x));
                        } else {

                        }
                    });
        }

        BindUtils.getInstance().bind(
                executeCommand.getCommand(),
                profiles,
                executeContext.getGlobalVariables(),
                executeScenario.getScenarioVariables());
    }

    /**
     * Flowスコープの変数を設定する.
     * プロセス実行時に指定を行うべきFlowスコープ変数の設定処理.
     *
     * @param context
     * @param scenario
     * @param executeCommand
     */
    private void settingFlowVariables(final Context context,
                                      final EXECUTE_CONTEXT executeContext,
                                      final EXECUTE_SCENARIO scenario,
                                      final EXECUTE_FLOW executeFlow,
                                      final EXECUTE_COMMAND executeCommand) {

        // 現在の処理コマンドのID
        executeFlow.getFlowVariables().put
                (FlowScopeVariables.CURRENT_COMMAND.getName(),
                        executeCommand.getCommand().getId());

        // 現在の処理コマンドの実行ID
        executeFlow.getFlowVariables().put(
                FlowScopeVariables.CURRENT_COMMAND_EXECUTE_ID.getName(),
                executeCommand.getExecuteId());
    }

    /**
     * シナリオスコープの変数を掃除する.
     * プロセス実行時にのみ指定すべきシナリオスコープの変数を確実に除去するための処理.
     *
     * @param context
     * @param scenario
     * @param executeFlow
     * @param executeCommand
     */
    private void cleanFlowVariables(
            final Context context,
            final EXECUTE_CONTEXT executeContext,
            final EXECUTE_SCENARIO scenario,
            final EXECUTE_FLOW executeFlow,
            final EXECUTE_COMMAND executeCommand) {

        // 現在の処理コマンドのID
        executeFlow.getFlowVariables().remove
                (FlowScopeVariables.CURRENT_COMMAND.getName());

        // 現在の処理プロセスの実行ID
        executeFlow.getFlowVariables().remove(
                FlowScopeVariables.CURRENT_COMMAND_EXECUTE_ID.getName());
    }

    /**
     * コマンド開始ログ出力.
     *
     * @param context
     * @param executeScenario
     * @param executeFlow
     * @param executeCommand
     */
    protected void outputStartProcessLog(
            final Context context,
            final EXECUTE_CONTEXT executeContext,
            final EXECUTE_SCENARIO executeScenario,
            final EXECUTE_FLOW executeFlow,
            final EXECUTE_COMMAND executeCommand) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------------------------------------------------------------------\n");
        sb.append("<<Start Process>>\n");
        sb.append("Scenario ID         : ");
        sb.append(executeScenario.getInfo().getId());
        sb.append("\n");
        sb.append("Process  ID         : ");
        sb.append(executeCommand.getCommand().getId());
        sb.append("\n");
        sb.append("Execute Command ID  : ");
        sb.append(executeCommand.getExecuteId());
        sb.append("\n");
        //sb.append("--------------------------------------------------------------------------------------");
        log.info(sb.toString());
    }

    /**
     * コマンド終了ログ出力.
     *
     * @param context
     * @param executeScenario
     * @param executeCommand
     */
    protected void outputEndProcessLog(
            final Context context,
            final EXECUTE_CONTEXT executeContext,
            final EXECUTE_SCENARIO executeScenario,
            final EXECUTE_FLOW executeFlow,
            final EXECUTE_COMMAND executeCommand) {

        StringBuilder sb = new StringBuilder();
        //sb.append("\n--------------------------------------------------------------------------------------\n");
        sb.append("\n<<End Process>>\n");
        sb.append("Scenario ID         : ");
        sb.append(executeScenario.getInfo().getId());
        sb.append("\n");
        sb.append("Process ID          : ");
        sb.append(executeCommand.getCommand().getId());
        sb.append("\n");
        sb.append("Execute Process ID  : ");
        sb.append(executeCommand.getExecuteId());
        sb.append("\n");
        sb.append("Process Status      : ");
        sb.append(executeCommand.getCommandResult().getStatus().name());
        sb.append("\n");
        sb.append("--------------------------------------------------------------------------------------");
        if (executeCommand.getCommandResult().getStatus() == CommandStatus.SUCCESS) {
            log.info(sb.toString());
        } else if (executeCommand.getCommandResult().getStatus() == CommandStatus.FAIL) {
            log.error(sb.toString());
        } else {
            log.warn(sb.toString());
        }

    }
}
