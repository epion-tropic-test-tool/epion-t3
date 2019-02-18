package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.AssertExistsStringInText;
import com.zomu.t.epion.tropic.test.tool.basic.command.model.StringConcat;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import org.slf4j.Logger;

import java.util.Map;

public class AssertExistsStringInTextRunner implements CommandRunner<AssertExistsStringInText> {

    @Override
    public void execute(
            AssertExistsStringInText command,
            Map<String, Object> globalScopeVariables,
            Map<String, Object> scenarioScopeVariables,
            Map<String, Object> flowScopeVariables,
            Map<String, EvidenceInfo> evidences,
            Logger logger) throws Exception {


    }
}
