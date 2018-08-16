package com.zomu.t.epion.tropic.test.tool.rest.command;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rest.bean.Header;
import com.zomu.t.epion.tropic.test.tool.rest.bean.QueryParameter;
import com.zomu.t.epion.tropic.test.tool.rest.runner.ExecuteRestApiRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import java.util.List;

@Getter
@Setter
@com.zomu.t.epion.tropic.test.tool.core.annotation.Command(id = "ExecuteRestApi", runner = ExecuteRestApiRunner.class)
public class ExecuteRestApi extends Command {

    @NotEmpty
    private String method;

    private String body;

    private List<QueryParameter> queryParameters;

    private List<Header> headers;

}
