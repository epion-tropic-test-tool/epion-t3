package com.zomu.t.epion.tropic.test.tool.basic.command.model;


import com.zomu.t.epion.tropic.test.tool.basic.command.reporter.AssertExistsStringInTextReporter;
import com.zomu.t.epion.tropic.test.tool.basic.command.runner.AssertExistsStringInTextRunner;
import com.zomu.t.epion.tropic.test.tool.basic.command.runner.ConsoleInputRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "AssertExistsStringInText",
        runner = AssertExistsStringInTextRunner.class,
        reporter = AssertExistsStringInTextReporter.class)
public class AssertExistsStringInText extends Command {

    /**
     * 正規表現として扱うかどうか.
     */
    private Boolean regexp;

    /**
     * 読み込みエンコーディング.
     */
    private String encoding;


}
