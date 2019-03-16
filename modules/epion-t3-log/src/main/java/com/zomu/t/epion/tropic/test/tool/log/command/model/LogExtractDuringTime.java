package com.zomu.t.epion.tropic.test.tool.log.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.log.command.runner.LogExtractDuringTimeRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 *
 */
@Getter
@Setter
@CommandDefinition(id = "LogExtractDuringTime", runner = LogExtractDuringTimeRunner.class)
public class LogExtractDuringTime extends Command {

    @NotEmpty
    private String targetFlow;

    @NotEmpty
    private String extractPattern;

    private int group = 1;

    private String datePattern = "yyyy-MM-ddTHH:mm:ss";

    /**
     * 前後バッファ数.
     */
    private int roundBuffer = 0;

    /**
     * 前後バッファ時間ユニット.
     */
    private String roundBufferTimeUnit = "seconds";

    /**
     * ファイルエンコーディング.
     */
    private String encoding = "UTF-8";

}
