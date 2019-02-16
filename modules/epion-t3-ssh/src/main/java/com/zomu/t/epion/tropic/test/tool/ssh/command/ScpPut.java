package com.zomu.t.epion.tropic.test.tool.ssh.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ssh.runner.ScpPutRunner;
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
     * SSH接続先設定参照ID.
     */
    private String sshConnectInfoRef;

    /**
     * リモートディレクトリ.
     */
    private String remoteDir;

    /**
     * ローカルファイル.
     */
    private String localFile;


}
