package com.epion_t3.ftp.command.model;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.ftp.command.runner.FtpGetRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "FtpGet", runner = FtpGetRunner.class)
public class FtpGet extends Command {

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
