package com.zomu.t.epion.tropic.test.tool.rdb.configuration.model;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * RDBへの接続定義.
 *
 * @author takashno
 */
@Getter
@Setter
public class RdbConnectionConfiguration extends Configuration {

    /** ドライバクラス名. */
    private String driverClassName;

    /** JDBC接続URL. */
    private String url;

    /** ユーザ名. */
    private String username;

    /** パスワード. */
    private String password;

    /** スキーマ. */
    private String schema;

    /** RDB種別. */
    private String rdbKind;

}
