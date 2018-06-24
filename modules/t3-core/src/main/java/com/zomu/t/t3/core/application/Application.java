package com.zomu.t.t3.core.application;

import com.zomu.t.t3.core.model.context.T3Context;
import com.zomu.t.t3.core.scenario.parser.ScenarioParser;
import com.zomu.t.t3.v10.model.context.T3ContextV10;
import com.zomu.t.t3.v10.parser.ScenarioParserV10;

import java.io.IOException;
import java.nio.file.Paths;

public class Application {


    public static void main(String[] args) throws IOException {


        // バージョンの解決（引数？）

        // コンテキストの生成
        T3Context context = new T3ContextV10(Paths.get("/Users/takashimanozomu/work/30_pgworkspaces/intellij/t3-core/modules/t3-core/src/main/resources/sample"));

        // シナリオの解析（パース処理）
        ScenarioParser scenarioParser = new ScenarioParserV10();
        scenarioParser.parse(context);

        // 実行シナリオの選択


    }

}
