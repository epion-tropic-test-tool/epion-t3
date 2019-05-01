package com.zomu.t.epion.tropic.test.tool.ftp.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.ftp.command.model.FtpGet;
import com.zomu.t.epion.tropic.test.tool.ftp.configuration.FtpConnectionConfiguration;
import com.zomu.t.epion.tropic.test.tool.ftp.utils.FtpUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;

/**
 * @author takashno
 */
public class FtpGetRunner extends AbstractCommandRunner<FtpGet> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(FtpGet command, Logger logger) throws Exception {

        // 接続先設定を参照
        FtpConnectionConfiguration ftpConnectionConfiguration =
                referConfiguration(command.getFtpConnectConfigRef());

        // FTPクライアントを作成
        FTPClient ftpClient = FtpUtils.getInstance().getFTPClient(ftpConnectionConfiguration);


        command.getRemotePath();


        return null;
    }

}
