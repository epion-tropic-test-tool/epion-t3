package com.zomu.t.t3.core.model.context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;

/**
 * 基底コンテキスト.
 */
public abstract class BaseContext implements Context {

    /**
     * YAML変換用の共通ObjectMapper.
     */
    private final ObjectMapper objectMapper;

    /**
     * 実行引数オプション.
     */
    private final Option option;

    public BaseContext() {
        this.option = createOption();
        this.objectMapper = createObjectMapper();
    }

    /**
     * ObjectMapperに手を加えたい場合は、オーバーライドすること.
     *
     * @return {@link ObjectMapper}
     */
    protected ObjectMapper createObjectMapper() {
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper objectMapper = new ObjectMapper(yamlFactory);

        // 知らない要素は無視する
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    /**
     * Optionの生成に手を加えたい場合は、オーバーライドすること.
     *
     * @return {@link Option}
     */
    protected Option createOption() {
        return new Option();
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    @Override
    public Option getOption() {
        return this.option;
    }
}
