package com.epion_t3.log.command.model;

import com.epion_t3.log.command.runner.LogExtractDuringTimeRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
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

    private Integer group = 1;

    private String datePattern = "yyyy-MM-ddTHH:mm:ss";

    /**
     * 前後バッファ数.
     */
    private Integer roundBuffer = 0;

    /**
     * 前後バッファ時間ユニット.
     */
    private String roundBufferTimeUnit = "seconds";

    /**
     * ファイルエンコーディング.
     */
    private String encoding = "UTF-8";

}
