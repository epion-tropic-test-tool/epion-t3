package com.zomu.t.t3.core.util;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

/**
 * 日付時間関連のユーティリティ.
 */
public final class DateTimeUtils {

    private static final DateTimeUtils instance = new DateTimeUtils();

    /**
     * 日時分秒ミリ秒までの標準フォーマット.
     */
    public static final DateTimeFormatter YYYYMMDD_HHMMSS_NORMAL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");


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
        return ChronoUnit.MILLIS.between(start, end);
    }

    public String formatNormal(Temporal target) {
        return YYYYMMDD_HHMMSS_NORMAL.format(target);
    }


}
