package com.epion_t3.ssh.command.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.ssh.command.model.ScpPut;
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
public class ScpPutRunner extends AbstractCommandRunner<ScpPut> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(final ScpPut command, final Logger logger) throws Exception {

        // 接続先情報を取得
        SshConnectionConfiguration sshConnectionConfiguration =
                referConfiguration(command.getSshConnectConfigRef());

        // 転送するローカルファイルを解決
        Path localFilePath = Paths.get(getCommandBelongScenarioDirectory(), command.getLocalFile()).normalize();

        try {
            ScpUtils.getInstance().put(sshConnectionConfiguration, command.getRemoteDir(), localFilePath.toString());
        } catch (IOException e) {
            throw new SystemException(e, SshMessages.SSH_ERR_0001);
        }

        return CommandResult.getSuccess();
    }
}
