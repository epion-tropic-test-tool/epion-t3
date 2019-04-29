package com.zomu.t.epion.tropic.test.tool.core.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class CommandSpecInfo implements Serializable {

    /**
     * コマンドID.
     */
    private String id;

    /**
     * 機能説明.
     */
    private Map<Locale, List<String>> functions = new ConcurrentHashMap<>();

    /**
     * 試験項目書文言.
     */
    private Map<Locale, List<String>> testItems = new ConcurrentHashMap<>();

    /**
     * コマンド構成.
     * 指定しなければいけない要素等を記載する.
     */
    private List<CommandSpecStructure> structures = new ArrayList<>();


    /**
     * 機能を追加.
     *
     * @param lang ロケール名
     * @param contents コンテンツ
     */
    public void addFunction(String lang, String contents) {
        Locale locale = Locale.forLanguageTag(lang);
        if (!functions.containsKey(locale)) {
            functions.put(locale, new ArrayList<>());
        }
        functions.get(locale).add(contents);
    }

    /**
     * 試験項目を追加.
     *
     * @param lang ロケール名
     * @param contents コンテンツ
     */
    public void addTestItem(String lang, String contents) {
        Locale locale = Locale.forLanguageTag(lang);
        if (!testItems.containsKey(locale)) {
            testItems.put(locale, new ArrayList<>());
        }
        testItems.get(locale).add(contents);
    }

    /**
     * コマンド構成を追加.
     *
     * @param commandSpecStructure
     */
    public void addStructure(CommandSpecStructure commandSpecStructure) {
        structures.add(commandSpecStructure);
    }
}
