package com.zomu.t.epion.tropic.test.tool.core.execution.parser;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;

/**
 * 個別対象用の解析クラス.
 *
 * @author takashno
 */
public interface IndividualTargetParser<C extends Context> {


    void parse(final C context);

    void parse(final C context, final String fileNamePattern);


}
