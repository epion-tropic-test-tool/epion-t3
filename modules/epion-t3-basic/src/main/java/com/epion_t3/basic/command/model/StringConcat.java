package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.StringConcatRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import java.util.List;

/**
 * 文字列結合コマンド.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(
        id = "StringConcat",
        runner = StringConcatRunner.class)
public class StringConcat extends Command {

    @NotEmpty
    private List<String> referenceVariables;

}
