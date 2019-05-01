package com.zomu.t.epion.tropic.test.tool.random.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomJapaneseLastNameRunner;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomJapaneseZipCodeRunner;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@CommandDefinition(id = "GenerateRandomJapaneseZipCode", runner = GenerateRandomJapaneseZipCodeRunner.class)
public class GenerateRandomJapaneseZipCode extends Command {
}
