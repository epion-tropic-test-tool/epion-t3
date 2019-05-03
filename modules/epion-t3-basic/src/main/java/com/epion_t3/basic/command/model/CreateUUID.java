package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.CreateUUIDRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "CreateUUID", runner = CreateUUIDRunner.class)
public class CreateUUID extends Command {
}
