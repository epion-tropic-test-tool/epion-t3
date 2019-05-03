package com.epion_t3.random.runner;

import com.epion_t3.core.common.context.EvidenceInfo;
import com.epion_t3.core.command.runner.CommandRunner;
import com.epion_t3.random.command.GenerateRandomString;
import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.basics.StringGenerator;
import org.slf4j.Logger;

import java.util.Map;

public class GenerateRandomStringRunner implements CommandRunner<GenerateRandomString> {

    @Override
    public void execute(
            final GenerateRandomString process,
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
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
