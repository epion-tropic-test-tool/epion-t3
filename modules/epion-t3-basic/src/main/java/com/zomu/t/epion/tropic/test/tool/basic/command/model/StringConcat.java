package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.StringConcatRunner;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import java.util.List;

/**
 * 文字列結合コマンド.
 */
@Getter
@Setter
@com.zomu.t.epion.tropic.test.tool.core.annotation.Command(id = "StringConcat", runner = StringConcatRunner.class)
public class StringConcat extends Command {

    @NotEmpty
    private List<String> referenceVariables;

}
