package com.zomu.t.epion.tropic.test.tool.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * FullコマンドIDパターン.
     */
    private static final Pattern FULL_COMMAND_ID_PATTERN = Pattern.compile("([^.]+)@([^.]+)");

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

    /**
     * FullコマンドIDを作成する.
     *
     * @param fqsn
     * @param commandId
     * @return
     */
    public String createFullCommandId(String fqsn, String commandId) {
        return fqsn + COMMAND_ID_JOINER + commandId;
    }

    /**
     * FullコマンドIDから属するシナリオIDを取得.
     *
     * @param fullQueryCommandId FullコマンドID
     * @return シナリオID
     */
    public String extractBelongScenarioId(String fullQueryCommandId) {
        if (!StringUtils.isNotEmpty(fullQueryCommandId)) {
            Matcher m = FULL_COMMAND_ID_PATTERN.matcher(fullQueryCommandId);
            if (m.find()) {
                return m.group(1);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * FullコマンドIDであるか判定する.
     *
     * @param target 対象文字列
     * @return 判定結果
     */
    public Boolean isFullQueryScenarioId(String target) {
        if (StringUtils.isNotEmpty(target)) {
            Matcher m = FULL_COMMAND_ID_PATTERN.matcher(target);
            return m.find();
        } else {
            return false;
        }
    }

}
