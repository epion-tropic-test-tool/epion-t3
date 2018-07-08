package com.zomu.t.t3.base.execution.perser;

import com.zomu.t.t3.base.context.BaseContext;
import com.zomu.t.t3.core.context.Context;
import com.zomu.t.t3.core.execution.parser.ScenarioParser;
import lombok.extern.slf4j.Slf4j;

/**
 * シナリオ解析処理.
 */
@Slf4j
public final class BaseScenarioParser implements ScenarioParser<BaseContext> {

    /**
     * インスタンス.
     */
    private static final BaseScenarioParser instance = new BaseScenarioParser();

    private BaseScenarioParser() {
        // Do Nothing...
    }

    /**
     * インスタンスを取得する.
     *
     * @return
     */
    public static BaseScenarioParser getInstance() {
        return instance;
    }

    /**
     * 解析処理.
     *
     * @param context
     */
    @Override
    public void parse(BaseContext context) {

        // カスタム定義の解析
        BaseCustomParser.getInstance().parse(context);

        // オリジナルの解析
        // 全シナリオファイルをここで一度解析する
        BaseOriginalHoldParser.getInstance().parse(context);

    }

}
