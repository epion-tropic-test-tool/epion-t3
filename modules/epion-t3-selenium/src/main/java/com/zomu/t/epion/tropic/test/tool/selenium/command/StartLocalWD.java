package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.selenium.runner.StartLocalWDRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "StartLocalWD", runner = StartLocalWDRunner.class)
public class StartLocalWD extends Command {

    @NotEmpty
    private String browser;
    
    private Integer height;

    private Integer width;

    private String driverPath;


}
