package com.zomu.t.t3.core.scenario.parser;

import com.zomu.t.t3.core.model.context.T3Context;
import com.zomu.t.t3.v10.model.scenario.Custom;

/**
 * 個別対象用の解析クラス.
 *
 * @author takashno
 */
public interface IndividualTargetParser {


    void parse(final T3Context context);

    void parse(final T3Context context, final String fileNamePattern);


}
