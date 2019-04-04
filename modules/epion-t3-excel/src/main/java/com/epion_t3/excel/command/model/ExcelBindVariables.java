package com.epion_t3.excel.command.model;

import com.epion_t3.excel.command.runner.ExcelBindVariablesRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "ExcelBindVariables", runner = ExcelBindVariablesRunner.class)
public class ExcelBindVariables extends Command {
}
