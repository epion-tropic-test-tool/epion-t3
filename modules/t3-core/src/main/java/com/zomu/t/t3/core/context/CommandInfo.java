package com.zomu.t.t3.core.context;

import com.zomu.t.t3.core.execution.runner.CommandRunner;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CommandInfo implements Serializable {

    @NonNull
    private String id;

    private String summary;

    private String description;

    @NonNull
    private Class<?> scenarioModel;

    @NonNull
    private Class<? extends CommandRunner> runner;

}
