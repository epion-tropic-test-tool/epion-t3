package com.zomu.t.epion.tropic.test.tool.ssh.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.ssh.command.model.ScpPutEvidence;
import com.zomu.t.epion.tropic.test.tool.ssh.configuration.model.SshConnectionConfiguration;
import com.zomu.t.epion.tropic.test.tool.ssh.messages.SshMessages;
import com.zomu.t.epion.tropic.test.tool.ssh.util.ScpUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

/**
 * エビデンス専用のSCPによる転送処理.
 *
 * @author takashno
 */
public class ScpPutEvidenceRunner extends AbstractCommandRunner<ScpPutEvidence> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(final ScpPutEvidence command, final Logger logger) throws Exception {

        // 接続先情報を取得
        SshConnectionConfiguration sshConnectionConfiguration =
                referConfiguration(command.getSshConnectConfigRef());

        // ファイル読み込み
        Path evidenceFile = referFileEvidence(command.getTarget());

        try {
            ScpUtils.getInstance().put(sshConnectionConfiguration, command.getRemoteDir(), evidenceFile.toString());
        } catch (IOException e) {
            throw new SystemException(e, SshMessages.SSH_ERR_0001);
        }

        return CommandResult.getSuccess();
    }
}
