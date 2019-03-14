package com.zomu.t.epion.tropic.test.tool.rest.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rest.bean.Header;
import com.zomu.t.epion.tropic.test.tool.rest.bean.QueryParameter;
import com.zomu.t.epion.tropic.test.tool.rest.bean.Request;
import com.zomu.t.epion.tropic.test.tool.rest.reporter.ExecuteRestApiReporter;
import com.zomu.t.epion.tropic.test.tool.rest.runner.ExecuteRestApiRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.List;

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

}
