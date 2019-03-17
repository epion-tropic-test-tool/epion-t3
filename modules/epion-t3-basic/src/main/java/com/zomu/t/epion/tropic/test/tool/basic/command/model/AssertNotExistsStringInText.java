package com.zomu.t.epion.tropic.test.tool.basic.command.model;


import com.zomu.t.epion.tropic.test.tool.basic.command.runner.AssertNotExistsStringInTextRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "AssertNotExistsStringInText",
        runner = AssertNotExistsStringInTextRunner.class,
        assertCommand = true)
public class AssertNotExistsStringInText extends Command {

    /**
     * 正規表現として扱うかどうか.
     */
    private Boolean regexp = false;

    /**
     * 読み込みエンコーディング.
     */
    private String encoding = "UTF-8";


}
