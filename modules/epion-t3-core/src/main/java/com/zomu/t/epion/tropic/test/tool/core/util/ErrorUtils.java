package com.zomu.t.epion.tropic.test.tool.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public final class ErrorUtils {

    private static final ErrorUtils instance = new ErrorUtils();

    private ErrorUtils() {
    }

    public static ErrorUtils getInstance() {
        return instance;
    }

    public String getStacktrace(Throwable t) {
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw);) {
            t.printStackTrace(pw);
            pw.flush();
            return sw.toString();
        } catch (IOException e) {
            // Ignore...
            log.debug("ignore exception...", e);
            return null;
        }
    }

}
