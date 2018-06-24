package com.zomu.t.t3.v10.parser;

import com.google.common.reflect.ClassPath;
import com.zomu.t.t3.core.model.context.CommandInfo;
import com.zomu.t.t3.core.model.context.T3Context;
import com.zomu.t.t3.core.scenario.parser.ScenarioParser;
import com.zomu.t.t3.core.annotation.Command;
import com.zomu.t.t3.v10.model.context.T3ContextV10;
import com.zomu.t.t3.core.model.context.holder.CustomConfigHolder;
import com.zomu.t.t3.v10.model.scenario.Process;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class ScenarioParserV10 implements ScenarioParser {


    @Override
    public void parse(T3Context context) {

        // コンテキストの生成
        T3ContextV10 t3ContextV10 = T3ContextV10.class.cast(context);

        // カスタム定義の解析
        CustomParserV10.parseCustom(t3ContextV10);


    }

}
