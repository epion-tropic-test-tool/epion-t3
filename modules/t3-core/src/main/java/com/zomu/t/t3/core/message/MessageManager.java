package com.zomu.t.t3.core.message;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * メッセージ管理クラス.
 *
 * @author takashno
 */
@Slf4j
public final class MessageManager {

    /**
     * メッセージ解決処理.
     */
    private static final MessageResolver resolver = MessageResolver.getInstance();

    /**
     * メッセージ管理インスタンス.
     */
    private static final MessageManager instance = new MessageManager();

    /**
     * プライベートコンストラクタ.
     */
    private MessageManager() {
        // Do Nothing...
    }

    /**
     * インスタンスを取得する.
     *
     * @return
     */
    public static MessageManager getInstance() {
        return instance;
    }

    /**
     * メッセージフォーマットを取得する.
     *
     * @param messageCode
     * @return
     */
    private MessageFormat getMessageFormat(String messageCode) {
        return new MessageFormat(resolver.getMessage(messageCode));
    }

    /**
     * メッセージを取得する.
     *
     * @param messageCode
     * @return
     */
    public String getMessage(String messageCode) {
        return getMessageFormat(messageCode).format(null);
    }

    /**
     * メッセージを取得する.
     *
     * @param messageCode
     * @param params
     * @return
     */
    public String getMessage(String messageCode, Object... params) {
        return getMessageFormat(messageCode).format(params);
    }

    /**
     * メッセージを取得する.
     *
     * @param messageCode
     * @param params
     * @return
     */
    public String getMessageWithCode(String messageCode, Object... params) {
        return "[" + messageCode + "] " + getMessageFormat(messageCode).format(params);
    }


}
