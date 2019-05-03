package com.epion_t3.selenium.command;

import com.epion_t3.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
public abstract class AbstractWDCommand extends Command {

    @NotEmpty
    private String refWebDriver;

    @NotEmpty
    private String selector;
}
