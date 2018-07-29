package com.zomu.t.epion.tropic.test.tool.core.holder;

import java.util.ArrayList;

public final class ProcessLoggingHolder {

    private static final ThreadLocal<ArrayList<ProcessLog>> messages = new ThreadLocal<ArrayList<ProcessLog>>() {
        @Override
        protected ArrayList<ProcessLog> initialValue() {
            return new ArrayList<>();
        }
    };

    public static void append(ProcessLog log) {
        messages.get().add(log);
    }

    public static void clear() {
        messages.remove();
    }

    public static ArrayList<ProcessLog> get() {
        return messages.get();
    }

}
