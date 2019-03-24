package com.zomu.t.epion.tropic.test.tool.selenium.command;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
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
