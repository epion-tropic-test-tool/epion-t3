package com.zomu.t.t3.basic.command.model;

import com.zomu.t.t3.basic.command.runner.FileCopyRunner;
import com.zomu.t.t3.core.annotation.Command;
import com.zomu.t.t3.model.scenario.Process;
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
