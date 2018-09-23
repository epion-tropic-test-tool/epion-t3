package com.zomu.t.epion.tropic.test.tool.rest.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rest.bean.Header;
import com.zomu.t.epion.tropic.test.tool.rest.bean.QueryParameter;
import com.zomu.t.epion.tropic.test.tool.rest.bean.Request;
import com.zomu.t.epion.tropic.test.tool.rest.runner.ExecuteRestApiRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@CommandDefinition(id = "ExecuteRestApi", runner = ExecuteRestApiRunner.class)
public class ExecuteRestApi extends Command {

    @NotNull
    @Valid
    private Request request;

}
