package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.ConsoleInput;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import org.slf4j.Logger;

import java.io.*;
import java.util.Map;

/**
 * コンソール入力コマンド実行クラス.
 * ユーザからのコンソール入力を受付、入力された文字列をシナリオスコープの変数に設定する.
 *
 * @author takashno
 */
public class ConsoleInputRunner implements CommandRunner<ConsoleInput> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final ConsoleInput process,
                        final Map<String, Object> globalScopeVariables,
                        final Map<String, Object> scenarioScopeVariables,
                        final Map<String, Object> flowScopeVariables,
                        final Map<String, EvidenceInfo> evidences,
                        final Logger logger) throws Exception {

        logger.info("start ConsoleInput");
        Console c = System.console();
        if (c != null) {
            // JDK1.6以降からのコンソールからシステムコンソールが取得できたならば、こちらを利用する
            String s = c.readLine(process.getTarget() + ": ");
            scenarioScopeVariables.put(process.getValue(), s);
        } else {
            // コンソールが取得できない場合は、従来の方法でコンソールからユーザ入力を取得する
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.print(process.getTarget() + ": ");
                String s = br.readLine();
                scenarioScopeVariables.put(process.getValue(), s);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        logger.info("end ConsoleInput");
    }

}
