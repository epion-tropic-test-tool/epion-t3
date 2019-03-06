package com.zomu.t.epion.tropic.test.tool.ftp.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.ftp.command.model.FtpGet;
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



//        FTPClient ftpClient = FtpUtils.getInstance().getFTPClient();

        return null;
    }

}
