package com.zomu.t.epion.tropic.test.tool.rest.command;

import java.util.List;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.rest.reporter.AssertResponseBodyJsonReporter;
import com.zomu.t.epion.tropic.test.tool.rest.runner.AssertResponseBodyJsonRunner;

import lombok.Getter;
import lombok.Setter;

/**
 * HTTPレスポンスボディ確認コマンド.
 *
 * @author 
 */
@Getter
@Setter
@CommandDefinition(id = "AssertResponseBodyJson",
        runner = AssertResponseBodyJsonRunner.class,
        reporter = AssertResponseBodyJsonReporter.class,
        assertCommand = true)
public class AssertResponseBodyJson extends Command {

	/**
	 * Json比較対象外のリスト.
	 */
	List<String> ignores;
}
