package com.zomu.t.epion.tropic.test.tool.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ScenarioScopeVariables {

    CURRENT_SCENARIO("currentScenario"),

    CURRENT_PROCESS("currentProcess"),

    CURRENT_PROCESS_EXECUTEID("currentProcessExecuteId"),

    SCENARIO_DIR("scenarioDir"),

    EVIDENCE_DIR("evidenceDir");

    private String name;

}
