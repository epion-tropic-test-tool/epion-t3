package com.zomu.t.epion.tropic.test.tool.ssh.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ssh.configuration.model.SshConnectionConfiguration;
import com.zomu.t.epion.tropic.test.tool.ssh.command.runner.ScpPutRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * SCPのPUTコマンド.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "ScpPut", runner = ScpPutRunner.class)
public class ScpPut extends Command {

    /**
     * SSH接続先情報参照ID.
     */
    @NotEmpty
    private String sshConnectConfigRef;

    /**
     * リモートディレクトリ.
     */
    @NotEmpty
    private String remoteDir;

    /**
     * ローカルファイル.
     */
    @NotEmpty
    private String localFile;


}
