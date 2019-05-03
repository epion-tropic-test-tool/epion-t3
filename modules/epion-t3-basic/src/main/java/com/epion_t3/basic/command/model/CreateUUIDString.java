package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.CreateUUIDStringRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "CreateUUIDString", runner = CreateUUIDStringRunner.class)
public class CreateUUIDString extends Command {
}
