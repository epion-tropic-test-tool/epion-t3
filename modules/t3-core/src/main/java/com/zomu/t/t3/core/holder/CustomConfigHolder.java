package com.zomu.t.t3.core.holder;

import com.zomu.t.t3.core.model.context.CommandInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CustomConfigHolder {


    private static final Map<String, String> customPackages = new ConcurrentHashMap<>();

    private static final Map<String, CommandInfo> customCommands = new ConcurrentHashMap<>();


    public static void addCustomPackage(String customName, String packageaName) {
        customPackages.put(customName, packageaName);
    }

    public static String getCustomPackage(String customName) {
        return customPackages.get(customName);
    }

    // -----------------------------------------------------------------------------------------------------------

    public static void addCustomCommandInfo(String commandId, CommandInfo commandInfo) {
        customCommands.put(commandId, commandInfo);
    }

    public static CommandInfo getCustomCommandInfo(String commandId) {
        return customCommands.get(commandId);
    }
}
