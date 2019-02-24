package com.zomu.t.epion.tropic.test.tool.core.command.model;

import com.zomu.t.epion.tropic.test.tool.core.type.AssertStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssertCommandResult extends CommandResult {

    private String expected;

    private String actual;

    private AssertStatus assertStatus = AssertStatus.WAIT;

}
