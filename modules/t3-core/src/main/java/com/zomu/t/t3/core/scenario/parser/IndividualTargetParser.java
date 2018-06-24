package com.zomu.t.t3.core.scenario.parser;

import com.zomu.t.t3.core.model.context.Context;

/**
 * 個別対象用の解析クラス.
 *
 * @author takashno
 */
public interface IndividualTargetParser {


    void parse(final Context context);

    void parse(final Context context, final String fileNamePattern);


}
