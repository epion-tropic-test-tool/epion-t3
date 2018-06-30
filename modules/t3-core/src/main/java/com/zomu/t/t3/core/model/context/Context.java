package com.zomu.t.t3.core.model.context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.nio.file.Path;

/**
 * 実行時の情報を保持するためのコンテキストクラス.
 *
 * @author takashno
 */
public interface Context extends Serializable {

    /**
     * シナリオ解析する際に利用するYAML解析クラス.
     */
    ObjectMapper getObjectMapper();


    /**
     * 実行引数のオプションを取得する.
     *
     * @return
     */
    Option getOption();

}
