package com.zomu.t.epion.tropic.test.tool.core.flow.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Flow;
import org.slf4j.Logger;

/**
 * フロー実行処理インタフェース.
 *
 * @param <FLOW>
 * @author takashno
 */
public interface FlowRunner<CONTEXT extends Context, SCENARIO extends ExecuteScenario, FLOW extends Flow> {

    /**
     * @param context
     * @param executeScenario
     * @param flow
     */
    void execute(
            final CONTEXT context,
            final SCENARIO executeScenario,
            final FLOW flow,
            final Logger logger);

}
