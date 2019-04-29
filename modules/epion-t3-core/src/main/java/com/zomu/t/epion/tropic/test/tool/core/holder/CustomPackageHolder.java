package com.zomu.t.epion.tropic.test.tool.core.holder;

import com.zomu.t.epion.tropic.test.tool.core.context.CommandInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.CustomSpecInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.FlowInfo;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * カスタムパッケージの保持クラス.
 *
 * @author takashno
 */
public final class CustomPackageHolder {

    /**
     * インスタンス.
     */
    private static final CustomPackageHolder instance = new CustomPackageHolder();

    /**
     * カスタムパッケージ.
     * Key：カスタム名
     * Value：カスタムパッケージ
     */
    private final Map<String, String> customPackages = new ConcurrentHashMap<>();

    /**
     * カスタムコマンド.
     */
    private final Map<String, CommandInfo> customCommands = new ConcurrentHashMap<>();

    /**
     * カスタムFlow.
     */
    private final Map<String, FlowInfo> customFlows = new ConcurrentHashMap<>();

    /**
     * カスタム設計情報.
     * Key: カスタム名
     * Value: カスタム設計情報
     */
    private final Map<String, CustomSpecInfo> customSpecInfos = new ConcurrentHashMap<>();




    /**
     * プライベートコンストラクタ.
     */
    private CustomPackageHolder() {
        // Do Nothing...
    }

    /**
     * インスタンスを取得する.
     *
     * @return シングルトンインスタンス
     */
    public static CustomPackageHolder getInstance() {
        return instance;
    }

    // -----------------------------------------------------------------------------------------------------------

    /**
     * カスタムパッケージを追加する.
     *
     * @param customName
     * @param packageaName
     */
    public void addCustomPackage(String customName, String packageaName) {
        customPackages.put(customName, packageaName);
    }

    /**
     * カスタムパッケージを取得する.
     *
     * @param customName
     * @return
     */
    public String getCustomPackage(String customName) {
        return customPackages.get(customName);
    }

    /**
     * カスタムパッケージマップを取得する.
     *
     * @return 変更不可Map
     */
    public Map<String, String> getCustomPackages() {
        return Collections.unmodifiableMap(customPackages);
    }

    // -----------------------------------------------------------------------------------------------------------

    public void addCustomCommandInfo(String commandId, CommandInfo commandInfo) {
        customCommands.put(commandId, commandInfo);
    }

    public CommandInfo getCustomCommandInfo(String commandId) {
        return customCommands.get(commandId);
    }

    // -----------------------------------------------------------------------------------------------------------

    /**
     * カスタムFlowを追加する.
     *
     * @param flowId
     * @param flowInfo
     */
    public void addCustomFlowInfo(String flowId, FlowInfo flowInfo) {
        customFlows.put(flowId, flowInfo);
    }

    /**
     * カスタムFlowを取得する.
     *
     * @param flowId
     * @return
     */
    public FlowInfo getCustomFlowInfo(String flowId) {
        return customFlows.get(flowId);
    }

    // -----------------------------------------------------------------------------------------------------------

    public void addCustomSpecInfo(String customName, CustomSpecInfo customSpecInfo) {
        customSpecInfos.put(customName, customSpecInfo);
    }

    public CustomSpecInfo getCustomSpecInfo(String customName) {
        return customSpecInfos.get(customName);
    }

    // -----------------------------------------------------------------------------------------------------------

}
