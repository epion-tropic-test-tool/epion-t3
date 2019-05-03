package com.epion_t3.ssh.command.model;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.ssh.command.runner.ScpPutRunner;
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
