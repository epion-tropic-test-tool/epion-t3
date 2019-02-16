package com.zomu.t.epion.tropic.test.tool.ssh.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.ssh.command.ScpPut;
import org.slf4j.Logger;

import java.util.Map;

public class ScpPutRunner implements CommandRunner<ScpPut> {

    @Override
    public void execute(ScpPut command, Map<String, Object> globalScopeVariables, Map<String, Object> scenarioScopeVariables, Map<String, Object> flowScopeVariables, Map<String, EvidenceInfo> evidences, Logger logger) throws Exception {

    }
}
