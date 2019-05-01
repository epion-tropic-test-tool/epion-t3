package com.zomu.t.epion.tropic.test.tool.ssh.command.model;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ssh.command.runner.EndSshRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id="EndSsh", runner = EndSshRunner.class)
public class EndSsh extends Command {
}
