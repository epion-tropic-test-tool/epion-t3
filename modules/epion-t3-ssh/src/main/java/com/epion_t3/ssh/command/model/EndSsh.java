package com.epion_t3.ssh.command.model;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.ssh.command.runner.EndSshRunner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@CommandDefinition(id="EndSsh", runner = EndSshRunner.class)
public class EndSsh extends Command {
}
