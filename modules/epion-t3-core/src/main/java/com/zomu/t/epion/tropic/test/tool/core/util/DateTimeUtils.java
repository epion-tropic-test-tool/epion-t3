package com.zomu.t.epion.tropic.test.tool.core.util;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

/**
 * 日付時間関連のユーティリティ.
 */
public final class DateTimeUtils {

    private static final DateTimeUtils instance = new DateTimeUtils();

    /**
     * 日時分秒ミリ秒までの標準フォーマット.
     */
    public static final DateTimeFormatter YYYYMMDD_HHMMSS_NORMAL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static final DateTimeFormatter HHMMSS_NORMAL = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");


    public static DateTimeUtils getInstance() {
        return instance;
    }

    private DateTimeUtils() {
        // Do Nothing...
    }

    /**
     * @param start
     * @param end
     * @return
     */
    public long getMillis(Temporal start, Temporal end) {
        if (start == null || end == null) {
            return 0L;
        }
        return ChronoUnit.MILLIS.between(start, end);
    }

    public String formatNormal(Temporal target) {
        if (target == null) {
            return null;
        }
        return YYYYMMDD_HHMMSS_NORMAL.format(target);
    }

    public String formatTimeNormal(Temporal target) {
        if (target == null) {
            return null;
        }
        return HHMMSS_NORMAL.format(target);
    }


}
