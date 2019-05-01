package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.CreateUUIDStringRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "CreateUUIDString", runner = CreateUUIDStringRunner.class)
public class CreateUUIDString extends Command {
}
