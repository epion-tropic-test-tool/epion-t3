package com.zomu.t.t3.core.context;

import com.zomu.t.t3.model.scenario.Custom;
import com.zomu.t.t3.model.scenario.Process;
import com.zomu.t.t3.model.scenario.T3Base;
import lombok.Getter;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * YAMLから読み込んだ情報の原本を保存するためのもの
 *
 * @author takashno
 */
@Getter
public class Original {

    /**
     * カスタム機能定義の原本.
     * この定義のみは、YAMLを解析する前に読み込まなければ、動的な型の解決ができない
     */
    private final Custom custom = new Custom();

    /**
     * ファイルそのままの原本.
     * キー：infoのid値
     * 値：ファイルの解析結果そのまま
     */
    private final Map<String, T3Base> originals = new ConcurrentHashMap<>();

    /**
     * typeがscenarioの原本.
     * キー：infoのid値
     * 値：ファイルの解析結果そのまま
     */
    private final Map<String, T3Base> scenarios = new ConcurrentHashMap<>();

    /**
     * typeがpartsの原本.
     * キー：infoのid値
     * 値：ファイルの解析結果そのまま
     */
    private final Map<String, T3Base> parts = new ConcurrentHashMap<>();

    /**
     * typeがconfigの原本.
     * キー：infoのid値
     * 値：ファイルの解析結果そのまま
     */
    private final Map<String, T3Base> configs = new ConcurrentHashMap<>();

    /**
     * processesの原本.
     * キー：infoのid + '-' + processesの要素のid値　= processの識別子
     */
    private final Map<String, Process> processes = new ConcurrentHashMap<>();


    /**
     * processの識別子とシナリオの関係マップ.
     * キー：infoのid + '-' + processesの要素のid値　= processの識別子
     * 値：infoのid
     */
    private final Map<String, String> processScenarioRelations = new ConcurrentHashMap<>();

    /**
     * scenario配置ディレクトリマップ.
     * キー：infoのid
     * 値：Path
     */
    private final Map<String, Path> scenarioPlacePaths = new ConcurrentHashMap<>();

    /**
     * process配置ディレクトリマップ.
     * キー：infoのid + '-' + processesの要素のid値　= processの識別子
     * 値：Path
     */
    private final Map<String, Path> processPlacePaths = new ConcurrentHashMap<>();

}
