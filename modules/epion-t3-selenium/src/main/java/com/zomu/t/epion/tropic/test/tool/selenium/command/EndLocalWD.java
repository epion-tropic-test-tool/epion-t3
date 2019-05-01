package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.EndLocalWDRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "EndLocalWD", runner = EndLocalWDRunner.class)
public class EndLocalWD extends Command {

    @NotEmpty
    private String refWebDriver;
}
