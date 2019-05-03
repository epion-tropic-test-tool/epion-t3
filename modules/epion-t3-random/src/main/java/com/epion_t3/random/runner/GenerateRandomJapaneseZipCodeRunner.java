package com.epion_t3.random.runner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.epion_t3.core.command.runner.CommandRunner;
import com.epion_t3.core.common.context.EvidenceInfo;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.random.bean.JapaneseZipCodeData;
import com.epion_t3.random.command.GenerateRandomJapaneseZipCode;
import com.epion_t3.random.message.RandomMessages;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class GenerateRandomJapaneseZipCodeRunner implements CommandRunner<GenerateRandomJapaneseZipCode> {

    private static final String japaneseZipCodeDataResourceFile = "japanese_zipcode_data.yml";

    @Override
    public void execute(
            final GenerateRandomJapaneseZipCode process,
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
                     GenerateRandomJapaneseZipCodeRunner.class.getClassLoader()
                             .getResourceAsStream(japaneseZipCodeDataResourceFile);) {
            JapaneseZipCodeData jzcd = objectMapper.readValue(is, JapaneseZipCodeData.class);
            String zipCode = jzcd.getZipcode().get(RandomUtils.nextInt(0, jzcd.getZipcode().size() - 1));
            logger.info("Generated ZipCode : {}", zipCode);
            scenarioScopeVariables.put(process.getTarget(), zipCode);
        } catch (IOException e) {
            throw new SystemException(RandomMessages.RANDOM_ERR_9002, e);
        }
        logger.info("end GenerateRandomJapaneseFirstName");
    }

}
