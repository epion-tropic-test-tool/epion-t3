package com.zomu.t.epion.tropic.test.tool.rest.reporter;

import java.util.Map;

import com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl.AbstractThymeleafCommandReporter;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.rest.bean.AssertResultResponseBodyJson;
import com.zomu.t.epion.tropic.test.tool.rest.command.AssertResponseBodyJson;

/**
 * AssertResponseBodyJsonコマンドのカスタムレポートクラス.
 * 
 * @author 
 */
public class AssertResponseBodyJsonReporter extends AbstractThymeleafCommandReporter<AssertResponseBodyJson, AssertResultResponseBodyJson> {

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public String templatePath() {
		return "assert-http-body-report-json";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVariables(Map<String, Object> variable,
							 AssertResponseBodyJson command,
							 AssertResultResponseBodyJson assertCommandResult,
							 ExecuteContext executeContext,
							 ExecuteScenario executeScenario,
							 ExecuteFlow executeFlow,
							 ExecuteCommand executeCommand) {
		
		// Expectedの格納
		variable.put("expected", assertCommandResult.getExpected());
		
		// Actualの格納
		variable.put("actual", assertCommandResult.getActual());
		
		// Json比較対象外リストの格納
		variable.put("ignores", command.getIgnores());
		
		// Assertion結果の詳細情報の格納
		variable.put("assertDetails", assertCommandResult.getJsonDifflist());
	}

}
