package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.StartLocalWDRunner;
import com.epion_t3.core.common.bean.scenario.Command;
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
