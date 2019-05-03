package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.FileDeleteRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
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
