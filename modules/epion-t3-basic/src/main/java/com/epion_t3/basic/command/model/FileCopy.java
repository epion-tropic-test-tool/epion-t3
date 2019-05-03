package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.FileCopyRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
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
