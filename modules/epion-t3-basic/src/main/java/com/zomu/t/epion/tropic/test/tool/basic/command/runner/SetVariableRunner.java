package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.SetVariable;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.impl.CoreMessages;
import com.zomu.t.epion.tropic.test.tool.core.type.ReferenceVariableType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Map;
import java.util.regex.Matcher;

/**
 * @author takashno
 */
public class SetVariableRunner implements CommandRunner<SetVariable> {

    @Override
    public void execute(
            final SetVariable command,
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            final Logger logger) throws Exception {

        if (StringUtils.isEmpty(command.getTarget())) {
            // TODO:Error
        }

        Matcher m = EXTRACT_PATTERN.matcher(command.getTarget());

        if (m.find()) {
            ReferenceVariableType referenceVariableType = ReferenceVariableType.valueOfByName(m.group(1));
            if (referenceVariableType != null) {
                switch (referenceVariableType) {
                    case FIX:
                        // Ignore
                        break;
                    case GLOBAL:
                        globalScopeVariables.put(m.group(2), command.getValue());
                        break;
                    case SCENARIO:
                        scenarioScopeVariables.put(m.group(2), command.getValue());
                        break;
                    case FLOW:
                        flowScopeVariables.put(m.group(2), command.getValue());
                        break;
                    default:
                        throw new SystemException(CoreMessages.CORE_ERR_0005, m.group(1));
                }
            } else {
                throw new SystemException(CoreMessages.CORE_ERR_0005, m.group(1));
            }
        }


    }
}
