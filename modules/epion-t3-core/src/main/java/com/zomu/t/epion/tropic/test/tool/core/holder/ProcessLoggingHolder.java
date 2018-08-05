package com.zomu.t.epion.tropic.test.tool.core.holder;

import java.util.ArrayList;

/**
 * プロセスログを保持するクラス.
 *
 * @author takashno
 */
public final class ProcessLoggingHolder {

    /**
     * プロセスログを保持するスレッドローカル.
     */
    private static final ThreadLocal<ArrayList<ProcessLog>> messages = new ThreadLocal<ArrayList<ProcessLog>>() {
        @Override
        protected ArrayList<ProcessLog> initialValue() {
            return new ArrayList<>();
        }
    };

    /**
     * ログ追加.
     *
     * @param log
     */
    public static void append(ProcessLog log) {
        messages.get().add(log);
    }

    /**
     * ログクリア.
     */
    public static void clear() {
        messages.remove();
    }

    /**
     * 取得.
     *
     * @return
     */
    public static ArrayList<ProcessLog> get() {
        return messages.get();
    }

}
