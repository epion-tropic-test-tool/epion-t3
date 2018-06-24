package com.zomu.t.t3.core.model.context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.nio.file.Path;

/**
 * T3実行時の情報を保持するためのコンテキストクラス.
 *
 * @author takashno
 */
public interface T3Context extends Serializable {

    /**
     * シナリオを配置しているルートパス.
     */
    Path getScenarioRootPath();

    /**
     * シナリオ解析する際に利用するYAML解析クラス.
     */
    ObjectMapper getObjectMapper();

}
