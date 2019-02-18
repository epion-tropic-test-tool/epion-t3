package com.zomu.t.epion.tropic.test.tool.ssh.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ssh.bean.SshConnectInfo;
import com.zomu.t.epion.tropic.test.tool.ssh.command.runner.ScpPutRunner;
import lombok.Getter;
import lombok.Setter;

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
    private String sshConnectInfoRef;

    /**
     * SSH接続先情報.
     */
    private SshConnectInfo sshConnectInfo;

    /**
     * リモートディレクトリ.
     */
    private String remoteDir;

    /**
     * ローカルファイル.
     */
    private String localFile;


}
