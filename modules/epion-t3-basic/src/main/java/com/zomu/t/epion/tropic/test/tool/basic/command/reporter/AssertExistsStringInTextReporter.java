package com.zomu.t.epion.tropic.test.tool.basic.command.reporter;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.AssertExistsStringInText;
import com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl.AbstractThymeleafCommandReporter;

import java.util.Map;

/**
 *
 */
public class AssertExistsStringInTextReporter
        extends AbstractThymeleafCommandReporter<AssertExistsStringInText> {

    @Override
    public String templatePath() {
        return "scenario";
    }

    @Override
    public void setVariables(Map<String, Object> variable) {
        // Do Nothing...
    }

}
