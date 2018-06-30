package com.zomu.t.t3.v10.runner;

import com.zomu.t.t3.core.exception.SystemException;
import com.zomu.t.t3.core.runner.ScenarioRunner;
import com.zomu.t.t3.v10.model.context.ContextV10;
import com.zomu.t.t3.v10.model.context.execute.ExecuteScenario;
import lombok.extern.slf4j.Slf4j;

/**
 * @author takashno
 */
@Slf4j
public class ScenarioRunnerV10 implements ScenarioRunner<ContextV10> {


    @Override
    public void execute(ContextV10 context) {


        if (context.getExecute() == null || context.getExecute().getScenarios() == null) {
            throw new SystemException("com.zomu.t.t3.err.9001");
        }

        for (ExecuteScenario scenario : context.getExecute().getScenarios()) {

        }


    }
}
