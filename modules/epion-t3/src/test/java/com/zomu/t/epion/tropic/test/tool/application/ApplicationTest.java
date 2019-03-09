package com.zomu.t.epion.tropic.test.tool.application;

import java.io.IOException;

/**
 * Applicationのテスト
 *
 * @author takashno
 */
public class ApplicationTest {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Application application = new Application();
        application.main(new String[]{
                "-v", "v1.0",
                "-m", "test",
                "-p", "develop",
                "-t", "scenarios-todos-refer-001",
                "-s", "/Users/takashimanozomu/work/30_pgworkspaces/intellij/epion-t3/modules/epion-t3-scenario"
        });
    }

}
