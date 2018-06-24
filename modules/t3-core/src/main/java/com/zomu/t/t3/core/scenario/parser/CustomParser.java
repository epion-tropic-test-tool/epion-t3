package com.zomu.t.t3.core.scenario.parser;

import com.zomu.t.t3.core.model.context.T3Context;
import com.zomu.t.t3.v10.model.scenario.Custom;

/**
 * Custom用の解析クラス.
 *
 * @author takashno
 */
public interface CustomParser {

    
    void parse(T3Context context);

    void parse(T3Context context, String fileNamePattern);


}
