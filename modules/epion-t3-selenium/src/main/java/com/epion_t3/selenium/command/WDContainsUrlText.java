package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.selenium.runner.WDContainsUrlTextRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
//@CommandDefinition(id = "WDContainsUrlText", runner = WDContainsUrlTextRunner.class)
public class WDContainsUrlText extends Command {

    @NotEmpty
    private String refWebDriver;

}
