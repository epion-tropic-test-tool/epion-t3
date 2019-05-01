package com.zomu.t.epion.tropic.test.tool.random.runner;

import com.zomu.t.epion.tropic.test.tool.core.common.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.random.command.GenerateRandomBirthdayString;
import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.person.BirthdayGenerator;
import org.slf4j.Logger;

import java.util.Map;

/**
 *
 */
public class GenerateRandomBirthdayStringRunner implements CommandRunner<GenerateRandomBirthdayString> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(
            final GenerateRandomBirthdayString process,
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Logger logger) throws Exception {

        logger.info("start GenerateRandomBirthdayString");
        BirthdayGenerator birthday = JRand.birthday();
        String generateBirthday = birthday.format(process.getFormat()).adult().genString();
        logger.info("Generated Birthday String : {}", generateBirthday);
        scenarioScopeVariables.put(process.getTarget(), generateBirthday);
        logger.info("end GenerateRandomBirthdayString");
    }
}
