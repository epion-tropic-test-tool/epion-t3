package com.epion_t3.random.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.random.runner.GenerateRandomJapaneseFirstNameRunner;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@CommandDefinition(id = "GenerateRandomJapaneseFirstName", runner = GenerateRandomJapaneseFirstNameRunner.class)
public class GenerateRandomJapaneseFirstName extends Command {

    private boolean male;

    private boolean female;

    public Boolean getMale() {
        return this.male;
    }

    public Boolean getFemale() {
        return this.female;
    }

}
