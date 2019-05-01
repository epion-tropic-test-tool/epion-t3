package com.zomu.t.epion.tropic.test.tool.core.common.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class CommandSpecStructure implements Serializable {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private String type;

    private String pattern;

    private Map<Locale, String> summaries = new ConcurrentHashMap<>();

    private Map<Locale, String> descriptions = new ConcurrentHashMap<>();

    /**
     * 概要を追加.
     *
     * @param lang ロケール名
     * @param contents コンテンツ
     */
    public void putSummary(String lang, String contents) {
        summaries.put(Locale.forLanguageTag(lang), contents);
    }

    /**
     * 詳細を追加.
     *
     * @param lang ロケール名
     * @param contents コンテンツ
     */
    public void putDescription(String lang, String contents) {
        descriptions.put(Locale.forLanguageTag(lang), contents);
    }

}
