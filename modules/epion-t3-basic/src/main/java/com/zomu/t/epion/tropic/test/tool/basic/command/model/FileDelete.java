package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.FileDeleteRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
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
