package com.zomu.t.epion.tropic.test.tool.rdb.util;

import com.zomu.t.epion.tropic.test.tool.rdb.configuration.model.RdbConnectionConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

/**
 * @author takashno
 */
@Slf4j
public final class RdbAccessUtils {

    /**
     * シングルトンインスタンス.
     */
    private static final RdbAccessUtils instance = new RdbAccessUtils();

    /**
     * プライベートコンストラクタ.
     */
    private RdbAccessUtils() {
    }

    /**
     * シングルトンインスタンスを取得.
     *
     * @return
     */
    public static RdbAccessUtils getInstance() {
        return instance;
    }

    /**
     * データソースを取得.
     *
     * @param rdbConnectionConfiguration RDB接続設定
     * @return {@link DataSource}
     */
    public DataSource getDataSource(RdbConnectionConfiguration rdbConnectionConfiguration) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(rdbConnectionConfiguration.getDriverClassName());
        dataSource.setUrl(rdbConnectionConfiguration.getUrl());
        dataSource.setUsername(rdbConnectionConfiguration.getUsername());
        dataSource.setPassword(rdbConnectionConfiguration.getPassword());
        return dataSource;
    }

}
