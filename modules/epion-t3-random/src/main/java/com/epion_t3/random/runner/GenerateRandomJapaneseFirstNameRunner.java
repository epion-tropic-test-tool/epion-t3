package com.epion_t3.random.runner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.epion_t3.core.command.runner.CommandRunner;
import com.epion_t3.core.common.context.EvidenceInfo;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.random.bean.JapaneseNameData;
import com.epion_t3.random.command.GenerateRandomJapaneseFirstName;
import com.epion_t3.random.command.GenerateRandomString;
import com.epion_t3.random.message.RandomMessages;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class GenerateRandomJapaneseFirstNameRunner implements CommandRunner<GenerateRandomJapaneseFirstName> {

    private static final String japaneseNameDataResourceFile = "japanese_name_data.yml";

    @Override
    public void execute(
            final GenerateRandomJapaneseFirstName process,
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Logger logger) throws Exception {

        logger.info("start GenerateRandomJapaneseFirstName");

        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper objectMapper = new ObjectMapper(yamlFactory);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try (InputStream is =
                     GenerateRandomJapaneseFirstNameRunner.class.getClassLoader()
                             .getResourceAsStream(japaneseNameDataResourceFile);) {
            JapaneseNameData jnd = objectMapper.readValue(is, JapaneseNameData.class);
            String firstName = null;
            if (process.getMale()) {
                firstName = jnd.getFirstName().getMale()
                        .get(RandomUtils.nextInt(0, jnd.getFirstName().getMale().size() - 1)).get(0);
            } else if (process.getFemale()) {
                firstName = jnd.getFirstName().getFemale()
                        .get(RandomUtils.nextInt(0, jnd.getFirstName().getFemale().size() - 1)).get(0);
            } else {
                if (RandomUtils.nextBoolean()) {
                    firstName = jnd.getFirstName().getMale()
                            .get(RandomUtils.nextInt(0, jnd.getFirstName().getMale().size() - 1)).get(0);
                } else {
                    firstName = jnd.getFirstName().getFemale()
                            .get(RandomUtils.nextInt(0, jnd.getFirstName().getFemale().size() - 1)).get(0);
                }
            }
            logger.info("Generated FirstName : {}", firstName);
            scenarioScopeVariables.put(process.getTarget(), firstName);
        } catch (IOException e) {
            throw new SystemException(RandomMessages.RANDOM_ERR_9001);
        }
        logger.info("end GenerateRandomJapaneseFirstName");
    }

}
