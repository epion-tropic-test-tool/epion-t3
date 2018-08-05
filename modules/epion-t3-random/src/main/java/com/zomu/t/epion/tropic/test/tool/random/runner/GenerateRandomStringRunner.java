package com.zomu.t.epion.tropic.test.tool.random.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.random.command.GenerateRandomString;
import com.zomu.t.epion.tropic.test.tool.random.command.GenerateRandomWord;
import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.basics.StringGenerator;
import me.xdrop.jrand.generators.text.WordGenerator;
import org.slf4j.Logger;

import java.util.Map;

public class GenerateRandomStringRunner implements CommandRunner<GenerateRandomString> {

    @Override
    public void execute(
            final GenerateRandomString process,
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Logger logger) throws Exception {

        logger.info("start GenerateRandomString");
        StringGenerator string = JRand.string();
        String generateString = string.length(process.getLength()).gen();
        logger.info("Generated String : {}", generateString);
        scenarioScopeVariables.put(process.getTarget(), generateString);
        logger.info("end GenerateRandomString");
    }
}
