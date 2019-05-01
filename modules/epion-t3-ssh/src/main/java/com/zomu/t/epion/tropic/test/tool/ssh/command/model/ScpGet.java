package com.zomu.t.epion.tropic.test.tool.ssh.command.model;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ssh.command.runner.ScpGetRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "ScpGet", runner = ScpGetRunner.class)
public class ScpGet extends Command {

    /**
     * SSH接続先情報参照ID.
     */
    @NotEmpty
    private String sshConnectConfigRef;

    /**
     * リモートファイル.
     */
    @NotEmpty
    private String remoteFile;

}
