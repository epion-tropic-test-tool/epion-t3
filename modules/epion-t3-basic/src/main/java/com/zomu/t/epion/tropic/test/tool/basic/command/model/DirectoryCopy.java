package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.DirectoryCopyRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;

@CommandDefinition(id = "DirectoryCopy", runner = DirectoryCopyRunner.class)
public class DirectoryCopy extends Command {
}
