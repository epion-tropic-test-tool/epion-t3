package com.zomu.t.epion.tropic.test.tool.core.util;

import lombok.extern.slf4j.Slf4j;

/**
 * ID関連のユーティリティ.
 */
@Slf4j
public final class IDUtils {

    /**
     * シングルトンインスタンス
     */
    private static final IDUtils instance = new IDUtils();

    /**
     * コマンドIDの
     */
    public static final String COMMAND_ID_JOINER = "@";

    /**
     * プライベートコンストラクタ
     */
    private IDUtils() {
        // Do Nothing...
    }

    /**
     * インスタンスを取得する.
     *
     * @return シングルトンインスタンス
     */
    public static IDUtils getInstance() {
        return instance;
    }


    public String createFullCommandId(String fqsn, String commandId) {
        return fqsn + COMMAND_ID_JOINER + commandId;
    }

}
