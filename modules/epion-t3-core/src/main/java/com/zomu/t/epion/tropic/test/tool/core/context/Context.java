package com.zomu.t.epion.tropic.test.tool.core.context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

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

    /**
     * シナリオを読み込んだオリジナル情報の保持クラスを取得する.
     *
     * @return
     */
    Original getOriginal();


}
