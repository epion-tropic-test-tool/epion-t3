package com.zomu.t.t3.base.context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.zomu.t.t3.core.context.CommandInfo;
import com.zomu.t.t3.core.context.Context;
import com.zomu.t.t3.core.context.Option;
import com.zomu.t.t3.core.context.Original;
import com.zomu.t.t3.core.context.execute.ExecuteContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基底コンテキスト.
 */
public class BaseContext implements Context {

    /**
     * YAML変換用の共通ObjectMapper.
     */
    @Getter
    private final ObjectMapper objectMapper;

    @Getter
    private final Original original = new Original();

    @Getter
    private final ExecuteContext executeOriginal = new ExecuteContext();

    @Getter
    @Setter
    private ExecuteContext executeContext;

    @Getter
    private final Map<String, CommandInfo> customCommands = new ConcurrentHashMap<>();

    /**
     * 実行引数オプション.
     */
    @Getter
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


}
