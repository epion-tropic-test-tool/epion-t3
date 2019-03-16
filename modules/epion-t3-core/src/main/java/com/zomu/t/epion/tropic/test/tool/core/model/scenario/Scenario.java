package com.zomu.t.epion.tropic.test.tool.core.model.scenario;

import java.io.Serializable;

public class Scenario implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 実行シナリオ.
     */
    private String ref;

    /**
     * プロファイル指定.
     */
    private String profile;

    /**
     * 実行モード
     */
    private String mode;

    /**
     * レポート出力しない
     */
    private String noreport;

    /**
     * デバッグ指定
     */
    private String debug;


}
