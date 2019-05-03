package com.epion_t3.basic.command.model;


import com.epion_t3.basic.command.runner.AssertExistsStringInTextRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "AssertExistsStringInText",
        runner = AssertExistsStringInTextRunner.class,
        assertCommand = true)
public class AssertExistsStringInText extends Command {

    /**
     * 正規表現として扱うかどうか.
     */
    private Boolean regexp = false;

    /**
     * 読み込みエンコーディング.
     */
    private String encoding = "UTF-8";


}
