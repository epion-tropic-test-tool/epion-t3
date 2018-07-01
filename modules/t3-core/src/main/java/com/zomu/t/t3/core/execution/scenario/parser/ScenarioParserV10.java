package com.zomu.t.t3.core.execution.scenario.parser;

import com.zomu.t.t3.core.model.context.BaseContext;
import com.zomu.t.t3.core.model.context.Context;
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
    public void parse(Context context) {

        // コンテキストの生成
        BaseContext t3ContextV10 = BaseContext.class.cast(context);

        // カスタム定義の解析
        CustomParserV10.parseCustom(t3ContextV10);

        // オリジナルの解析
        // 全シナリオファイルをここで一度解析する
        OriginalHoldParserV10.parseOriginal(t3ContextV10);

    }

}
