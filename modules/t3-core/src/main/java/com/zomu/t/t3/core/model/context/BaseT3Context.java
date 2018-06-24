package com.zomu.t.t3.core.model.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;

import java.nio.file.Path;

public abstract class BaseT3Context implements T3Context {

    private final ObjectMapper objectMapper;

    @Getter
    private final Path scenarioRootPath;

    public BaseT3Context(Path rootPath) {
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper objectMapper = new ObjectMapper(yamlFactory);
        this.objectMapper = objectMapper;
        this.scenarioRootPath = rootPath;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
