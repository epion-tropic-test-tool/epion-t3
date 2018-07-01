package com.zomu.t.t3.basic.command.model;

import com.zomu.t.t3.basic.command.runner.FileDeleteRunner;
import com.zomu.t.t3.core.annotation.Command;
import com.zomu.t.t3.model.scenario.Process;
import lombok.Getter;
import lombok.Setter;

/**
 * ファイル削除コマンド定義.
 *
 * @author takashno
 */
@Getter
@Setter
@Command(id = "FileDelete", runner = FileDeleteRunner.class)
public class FileDelete extends Process {
}
