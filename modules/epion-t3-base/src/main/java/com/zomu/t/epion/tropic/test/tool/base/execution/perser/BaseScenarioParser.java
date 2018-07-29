package com.zomu.t.epion.tropic.test.tool.base.execution.perser;

import com.zomu.t.epion.tropic.test.tool.base.context.BaseContext;
import com.zomu.t.epion.tropic.test.tool.base.message.BaseMessages;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.execution.parser.ScenarioParser;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;

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

        // ルートディレクトリの存在チェック
        if (!Files.exists(Paths.get(context.getOption().getRootPath()))) {
            throw new SystemException(BaseMessages.BASE_ERR_9005, context.getOption().getRootPath());
        }

        // カスタム定義の解析
        BaseCustomParser.getInstance().parse(context);

        // オリジナルの解析
        // 全シナリオファイルをここで一度解析する
        BaseOriginalHoldParser.getInstance().parse(context);

    }

}
