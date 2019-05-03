package com.epion_t3.random.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.random.runner.GenerateRandomJapaneseLastNameRunner;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@CommandDefinition(id = "GenerateRandomJapaneseLastName", runner = GenerateRandomJapaneseLastNameRunner.class)
public class GenerateRandomJapaneseLastName extends Command {
}
