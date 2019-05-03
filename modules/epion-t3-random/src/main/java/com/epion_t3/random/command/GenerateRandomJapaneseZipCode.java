package com.epion_t3.random.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.random.runner.GenerateRandomJapaneseLastNameRunner;
import com.epion_t3.random.runner.GenerateRandomJapaneseZipCodeRunner;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@CommandDefinition(id = "GenerateRandomJapaneseZipCode", runner = GenerateRandomJapaneseZipCodeRunner.class)
public class GenerateRandomJapaneseZipCode extends Command {
}
