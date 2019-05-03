package com.epion_t3.ssh.command.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.ssh.command.model.ScpGet;
import com.epion_t3.ssh.configuration.model.SshConnectionConfiguration;
import com.epion_t3.ssh.messages.SshMessages;
import com.epion_t3.ssh.util.ScpUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * SCPによる転送処理.
 *
 * @author takashno
 */
public class ScpGetRunner extends AbstractCommandRunner<ScpGet> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(
            final ScpGet command,
            final Logger logger) throws Exception {

        // 接続先情報を取得
        SshConnectionConfiguration sshConnectionConfiguration =
                referConfiguration(command.getSshConnectConfigRef());

        // 転送するローカルファイルを解決
        Path localFilePath = getEvidencePath(Paths.get(command.getRemoteFile()).getFileName().toString());

        try {
            ScpUtils.getInstance().get(sshConnectionConfiguration, command.getRemoteFile(), localFilePath.toString());
            registrationFileEvidence(localFilePath);
        } catch (IOException e) {
            throw new SystemException(e, SshMessages.SSH_ERR_0002);
        }

        return CommandResult.getSuccess();
    }
}
