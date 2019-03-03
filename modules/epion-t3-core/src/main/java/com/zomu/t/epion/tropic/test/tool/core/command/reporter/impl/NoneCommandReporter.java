package com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl;

import java.util.Map;

/**
 * 何も行わないデフォルトのレポート出力処理クラス.
 *
 * @author takashno
 */
public class NoneCommandReporter extends AbstractCommandReporter {

    /**
     * {@inheritDoc}
     */
    @Override
    public String templatePath() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVariables(Map variable) {
        // Do Nothings...
    }
}
