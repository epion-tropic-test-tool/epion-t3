package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.FileDeleteRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

/**
 * ファイル削除コマンド定義.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "FileDelete", runner = FileDeleteRunner.class)
public class FileDelete extends Command {
}
