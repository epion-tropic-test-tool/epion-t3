package com.epion_t3.selenium.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.selenium.runner.WDScreenShotRunner;
import com.epion_t3.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "WDScreenShot", runner = WDScreenShotRunner.class)
public class WDScreenShot extends Command {

    @NotEmpty
    private String refWebDriver;
}
