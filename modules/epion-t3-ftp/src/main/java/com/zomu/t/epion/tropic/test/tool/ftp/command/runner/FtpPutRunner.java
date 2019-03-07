package com.zomu.t.epion.tropic.test.tool.ftp.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.ftp.command.model.FtpGet;
import com.zomu.t.epion.tropic.test.tool.ftp.command.model.FtpPut;
import com.zomu.t.epion.tropic.test.tool.ftp.configuration.FtpConnectionConfiguration;
import com.zomu.t.epion.tropic.test.tool.ftp.messages.FtpMessages;
import com.zomu.t.epion.tropic.test.tool.ftp.utils.FtpUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author takashno
 */
public class FtpPutRunner extends AbstractCommandRunner<FtpPut> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(FtpPut command, Logger logger) throws Exception {

        // 転送対象のローカルファイルの解決
        Path localPath = Paths.get(command.getLocalPath());
        if (!localPath.isAbsolute()) {
            // 相対パス
            localPath = Paths.get(getCommandBelongScenarioDirectory(), command.getLocalPath()).normalize();
        }

        // 存在チェック
        if (Files.notExists(localPath)) {
            throw new SystemException(FtpMessages.FTP_ERR_9003, localPath.toString());
        }

        // 接続先設定を参照
        FtpConnectionConfiguration ftpConnectionConfiguration =
                referConfiguration(command.getFtpConnectConfigRef());

        // FTPクライアントを作成
        FTPClient ftpClient = null;

        try {

            ftpClient = FtpUtils.getInstance().getFTPClient(ftpConnectionConfiguration);

        } finally {
            if (ftpClient != null) {
                if (ftpClient.logout()) {
                    ftpClient.disconnect();
                }
                ftpClient = null;
            }
        }


        ftpClient.getStatus();


        return null;
    }

}
