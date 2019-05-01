package com.zomu.t.epion.tropic.test.tool.rdb.configuration.model;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CustomConfigurationDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * RDBへの接続定義.
 *
 * @author takashno
 */
@Getter
@Setter
@CustomConfigurationDefinition(id = "RdbConnectionConfiguration")
public class RdbConnectionConfiguration extends Configuration {

    /**
     * ドライバクラス名.
     */
    private String driverClassName;

    /**
     * JDBC接続URL.
     */
    private String url;

    /**
     * ユーザ名.
     */
    private String username;

    /**
     * パスワード.
     */
    private String password;

    /**
     * スキーマ.
     */
    private String schema;

    /**
     * RDB種別.
     */
    private String rdbKind;

}
