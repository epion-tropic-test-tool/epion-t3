package com.zomu.t.epion.tropic.test.tool.ftp.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ftp.command.runner.FtpGetRunner;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "FtpGet", runner = FtpGetRunner.class)
public class FtpGet extends Command {

    /**
     *
     */
    private Integer fileType = null;

    /**
     * リモートのパス.
     */
    private String remotePath = null;

    /**
     * ローカルのパス.
     */
    private String localPath = null;


}
