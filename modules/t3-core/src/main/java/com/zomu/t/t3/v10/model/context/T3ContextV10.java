package com.zomu.t.t3.v10.model.context;

import com.zomu.t.t3.core.model.context.BaseT3Context;

import com.zomu.t.t3.core.model.context.CommandInfo;
import lombok.Getter;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class T3ContextV10 extends BaseT3Context {

    @Getter
    private final T3ContextV10Original original = new T3ContextV10Original();

    @Getter
    private final Map<String, CommandInfo> customCommands = new ConcurrentHashMap<>();


    public T3ContextV10(Path rootPath) {
        super(rootPath);
    }


}
