package com.zomu.t.epion.tropic.test.tool.rest.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rest.bean.Request;
import com.zomu.t.epion.tropic.test.tool.rest.reporter.ExecuteRestApiReporter;
import com.zomu.t.epion.tropic.test.tool.rest.runner.ExecuteRestApiRunner;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "ExecuteRestApi",
        runner = ExecuteRestApiRunner.class,
        reporter = ExecuteRestApiReporter.class)
public class ExecuteRestApi extends Command {

    @NotNull
    @Valid
    private Request request;

    /**
     * 接続タイムアウト.
     * Valiableのバインドを利用したい場合があるんではなかろうか？と思い文字列としている.
     */
    private String connectTimeout = "3000";

    /**
     * 読み込みタイムアウト.
     * Valiableのバインドを利用したい場合があるんではなかろうか？と思い文字列としている.
     */
    private String readTimeout = "3000";

    /**
     * ボディのエンコーディング指定.
     */
    private String bodyEncoding;

}
