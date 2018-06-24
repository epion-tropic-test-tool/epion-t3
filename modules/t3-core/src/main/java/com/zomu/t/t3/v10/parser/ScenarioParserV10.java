package com.zomu.t.t3.v10.parser;

import com.zomu.t.t3.core.model.context.T3Context;
import com.zomu.t.t3.core.scenario.parser.ScenarioParser;
import com.zomu.t.t3.v10.model.context.T3ContextV10;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
public class ScenarioParserV10 implements ScenarioParser {

    /**
     * 解析処理.
     *
     * @param context
     */
    @Override
    public void parse(T3Context context) {

        // コンテキストの生成
        T3ContextV10 t3ContextV10 = T3ContextV10.class.cast(context);

        // カスタム定義の解析
        CustomParserV10.parseCustom(t3ContextV10);

        // オリジナルの解析
        // 全シナリオファイルをここで一度解析する
        OriginalHoldParserV10.parseOriginal(t3ContextV10);

    }

}
