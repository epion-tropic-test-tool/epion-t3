package com.zomu.t.t3.core.model.context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;

import java.nio.file.Path;

public abstract class BaseContext implements Context {

    private final ObjectMapper objectMapper;

    @Getter
    private final Path scenarioRootPath;

    public BaseContext(Path rootPath) {
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper objectMapper = new ObjectMapper(yamlFactory);

        // 知らない要素は無視する
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        this.objectMapper = objectMapper;
        this.scenarioRootPath = rootPath;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
