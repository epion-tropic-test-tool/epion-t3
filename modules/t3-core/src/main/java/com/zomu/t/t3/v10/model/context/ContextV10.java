package com.zomu.t.t3.v10.model.context;

import com.zomu.t.t3.core.model.context.BaseContext;

import com.zomu.t.t3.core.model.context.CommandInfo;
import lombok.Getter;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class ContextV10 extends BaseContext {

    @Getter
    private final OriginalV10 original = new OriginalV10();

    @Getter
    private final Map<String, CommandInfo> customCommands = new ConcurrentHashMap<>();


    public ContextV10(Path rootPath) {
        super(rootPath);
    }


}
