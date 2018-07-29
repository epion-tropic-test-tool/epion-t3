package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.FileCopyRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import lombok.Getter;
import lombok.Setter;

/**
 * ファイルコピーコマンド定義.
 *
 * @author takashno
 */
@Getter
@Setter
@Command(id = "FileCopy", runner = FileCopyRunner.class)
public class FileCopy extends Process {

    /**
     * コピー元パス.
     */
    private String from;

    /**
     * コピー先パス.
     */
    private String to;
    

}
