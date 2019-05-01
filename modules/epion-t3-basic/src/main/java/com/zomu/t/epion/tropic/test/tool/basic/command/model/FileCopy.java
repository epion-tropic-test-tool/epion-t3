package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.FileCopyRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

/**
 * ファイルコピーコマンド定義.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "FileCopy", runner = FileCopyRunner.class)
public class FileCopy extends Command {

    /**
     * コピー元パス.
     */
    private String from;

    /**
     * コピー先パス.
     */
    private String to;
    

}
