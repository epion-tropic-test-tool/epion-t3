package com.zomu.t.epion.tropic.test.tool.ssh.command.model;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ssh.command.runner.ScpPutEvidenceRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * エビデンス専用のSCPのPUTコマンド.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "ScpPutEvidence", runner = ScpPutEvidenceRunner.class)
public class ScpPutEvidence extends Command {

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


}
