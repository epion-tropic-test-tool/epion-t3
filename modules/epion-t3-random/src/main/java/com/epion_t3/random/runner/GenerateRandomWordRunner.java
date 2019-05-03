package com.epion_t3.random.runner;

import com.epion_t3.core.common.context.EvidenceInfo;
import com.epion_t3.core.command.runner.CommandRunner;
import com.epion_t3.random.command.GenerateRandomWord;
import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.text.WordGenerator;
import org.slf4j.Logger;

import java.util.Map;

public class GenerateRandomWordRunner implements CommandRunner<GenerateRandomWord> {

    @Override
    public void execute(
            final GenerateRandomWord process,
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Logger logger) throws Exception {

        logger.info("start GenerateRandomWord");
        WordGenerator word = JRand.word();
        String generateWord = word.length(process.getLength()).gen();
        logger.info("Generated Word : {}", generateWord);
        scenarioScopeVariables.put(process.getTarget(), generateWord);
        logger.info("end GenerateRandomWord");
    }
}
