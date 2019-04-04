package com.zomu.t.epion.tropic.test.tool.ssh.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ssh.command.runner.StartSshRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "StartSsh", runner = StartSshRunner.class)
public class StartSsh extends Command {

    /**
     * SSH接続先情報参照ID.
     */
    @NotEmpty
    private String sshConnectConfigRef;

}
