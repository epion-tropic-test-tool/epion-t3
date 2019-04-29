package com.zomu.t.epion.tropic.test.tool.core.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class CommandSpecStructure implements Serializable {

    private String name;

    private String type;

    private String pattern;

    private Map<Locale, String> summaries = new ConcurrentHashMap<>();

    /**
     * 概要を追加.
     *
     * @param lang ロケール名
     * @param contents コンテンツ
     */
    public void putSummary(String lang, String contents) {
        summaries.put(Locale.forLanguageTag(lang), contents);
    }

}
