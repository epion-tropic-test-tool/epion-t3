package com.zomu.t.epion.tropic.test.tool.core.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * カスタマイズ機能の情報
 *
 * @author takashno
 */
@Getter
@Setter
public class CustomSpecInfo implements Serializable {

    private String name;

    private String customPackage;

    private final Map<Locale, String> summaries = new ConcurrentHashMap<>();

    private final Map<Locale, String> descriptions = new ConcurrentHashMap<>();

    /**
     * コマンド情報
     */
    private final List<CommandSpecInfo> commands = new ArrayList<>();

    /**
     * メッセージ.
     */
    private Map<Locale, List<Message>> messages = new ConcurrentHashMap<>();

    /**
     * 概要を追加.
     *
     * @param lang     ロケール名
     * @param contents コンテンツ
     */
    public void putSummary(String lang, String contents) {
        summaries.put(Locale.forLanguageTag(lang), contents);
    }

    /**
     * 詳細を追加.
     *
     * @param lang     ロケール名
     * @param contents コンテンツ
     */
    public void putDescription(String lang, String contents) {
        descriptions.put(Locale.forLanguageTag(lang), contents);
    }

    public void addCommandSpecInfo(CommandSpecInfo commandSpecInfo) {
        commands.add(commandSpecInfo);
    }

    /**
     * メッセージを追加.
     *
     * @param lang
     * @param id
     * @param content
     */
    public void addMessage(String lang, String id, String content) {
        Locale locale = Locale.forLanguageTag(lang);
        if (!messages.containsKey(locale)) {
            messages.put(locale, new ArrayList<>());
        }
        Message message = new Message();
        message.setId(id);
        message.setContent(content);
        messages.get(locale).add(message);
    }

}
