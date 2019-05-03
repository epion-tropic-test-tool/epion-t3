package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.DirectoryCreateRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;

@CommandDefinition(id = "DirectoryCreate", runner = DirectoryCreateRunner.class)
public class DirectoryCreate extends Command {
}
