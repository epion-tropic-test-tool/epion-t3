package com.zomu.t.epion.tropic.test.tool.random.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomJapaneseLastNameRunner;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@CommandDefinition(id = "GenerateRandomJapaneseLastName", runner = GenerateRandomJapaneseLastNameRunner.class)
public class GenerateRandomJapaneseLastName extends Command {
}
