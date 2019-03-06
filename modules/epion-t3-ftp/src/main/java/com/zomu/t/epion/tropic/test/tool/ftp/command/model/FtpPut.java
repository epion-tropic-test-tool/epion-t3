package com.zomu.t.epion.tropic.test.tool.ftp.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ftp.command.runner.FtpGetRunner;
import com.zomu.t.epion.tropic.test.tool.ftp.command.runner.FtpPutRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "FtpPut", runner = FtpPutRunner.class)
public class FtpPut extends Command {

    /**
     * FTP接続設定参照.
     */
    @NotEmpty
    private String ftpConnectConfigRef;

    /**
     *
     */
    @NotNull
    private Integer fileType = null;

    /**
     * リモートのパス.
     */
    @NotEmpty
    private String remotePath = null;

    /**
     * ローカルのパス.
     */
    @NotEmpty
    private String localPath = null;


}
