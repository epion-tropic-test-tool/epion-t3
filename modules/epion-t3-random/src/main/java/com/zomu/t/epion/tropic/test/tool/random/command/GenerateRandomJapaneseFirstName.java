package com.zomu.t.epion.tropic.test.tool.random.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomJapaneseFirstNameRunner;
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
