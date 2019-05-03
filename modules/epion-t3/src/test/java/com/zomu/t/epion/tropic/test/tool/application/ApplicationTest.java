package com.epion_t3.application;

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
//                "-i", "-o", "C:\\Users\\takashno\\Desktop\\test"
                "-v", "v1.0",
                "-m", "test",
                "-p", "develop",
                "-t", "scenarios-selenium-sample",
                "-s", "/Users/takashimanozomu/work/30_pgworkspaces/intellij/epion-t3/modules/epion-t3-scenario/samples/unit"
        });
    }

}
