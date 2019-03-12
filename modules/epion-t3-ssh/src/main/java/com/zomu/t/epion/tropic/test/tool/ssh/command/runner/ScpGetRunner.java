package com.zomu.t.epion.tropic.test.tool.ssh.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.ssh.command.model.ScpGet;
import com.zomu.t.epion.tropic.test.tool.ssh.command.model.ScpPut;
import com.zomu.t.epion.tropic.test.tool.ssh.configuration.model.SshConnectionConfiguration;
import com.zomu.t.epion.tropic.test.tool.ssh.messages.SshMessages;
import com.zomu.t.epion.tropic.test.tool.ssh.util.ScpUtils;
import org.apache.commons.io.FileUtils;
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
        } catch (IOException e) {
            throw new SystemException(e, SshMessages.SSH_ERR_0002);
        }

        return CommandResult.getSuccess();
    }
}
