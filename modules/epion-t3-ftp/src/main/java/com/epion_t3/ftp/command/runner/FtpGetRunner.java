package com.epion_t3.ftp.command.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.ftp.command.model.FtpGet;
import com.epion_t3.ftp.configuration.FtpConnectionConfiguration;
import com.epion_t3.ftp.utils.FtpUtils;
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
