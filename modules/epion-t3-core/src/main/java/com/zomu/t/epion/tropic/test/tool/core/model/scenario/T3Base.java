package com.zomu.t.epion.tropic.test.tool.core.model.scenario;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.zomu.t.epion.tropic.test.tool.core.execution.resolver.impl.CommandTypeIdResolver;
import com.zomu.t.epion.tropic.test.tool.core.execution.resolver.impl.FlowTypeIdResolver;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * シナリオモデル.
 */
@Getter
@Setter
public class T3Base implements Serializable {

    /**
     * バージョン.
     * 今使えていない・・・
     */
    private String t3 = "1.0";

    /**
     * シナリオ種別.
     * 今きちんと使えていない
     */
    private String type;

    /**
     * 情報.
     * 必須にしたいけど微妙
     */
    private Information info;

    // MEMO:visible属性を「true」にしないとパースした際に値が設定されないらしい
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
    @JsonTypeIdResolver(FlowTypeIdResolver.class)
    @Valid
    private List<Flow> flows = new ArrayList<>();

    // MEMO:visible属性を「true」にしないとパースした際に値が設定されないらしい
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "command", visible = true)
    @JsonTypeIdResolver(CommandTypeIdResolver.class)
    @Valid
    private List<Command> commands = new ArrayList<>();

    private Variable variables;

    private Profile profiles;

    private Custom customs;

}
