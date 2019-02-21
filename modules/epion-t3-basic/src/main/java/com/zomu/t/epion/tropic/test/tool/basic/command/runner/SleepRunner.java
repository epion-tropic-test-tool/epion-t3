package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.Sleep;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Map;

/**
 * Sleepコマンド処理.
 *
 * @author takashno
 */
public class SleepRunner extends AbstractCommandRunner<Sleep> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(
            Sleep command,
            Logger logger) throws Exception {

        if (StringUtils.isEmpty(command.getValue())) {
            // TODO:Error
        }

        if (!StringUtils.isNumeric(command.getValue())) {
            // TODO:Error
        }

        // Sleep...
        Thread.sleep(Long.valueOf(command.getValue()));

    }
}
