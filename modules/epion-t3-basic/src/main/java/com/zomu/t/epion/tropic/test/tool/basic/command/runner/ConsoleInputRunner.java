package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.ConsoleInput;
import com.zomu.t.epion.tropic.test.tool.basic.messages.BasicMessages;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import org.slf4j.Logger;

import java.io.*;
import java.util.Map;

/**
 * コンソール入力コマンド実行クラス.
 * ユーザからのコンソール入力を受付、入力された文字列をシナリオスコープの変数に設定する.
 *
 * @author takashno
 */
public class ConsoleInputRunner extends AbstractCommandRunner<ConsoleInput> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(final ConsoleInput process,
                                 final Logger logger) throws Exception {

        Console c = System.console();
        if (c != null) {
            // JDK1.6以降からのコンソールからシステムコンソールが取得できたならば、こちらを利用する
            String s = c.readLine(process.getTarget() + ": ");
            getScenarioScopeVariables().put(process.getValue(), s);
        } else {
            // コンソールが取得できない場合は、従来の方法でコンソールからユーザ入力を取得する
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.print(process.getTarget() + ": ");
                String s = br.readLine();
                getScenarioScopeVariables().put(process.getValue(), s);
            } catch (Exception e) {
                throw new SystemException(e, BasicMessages.BASIC_ERR_9006);
            }
        }
        return CommandResult.getSuccess();
    }

}
