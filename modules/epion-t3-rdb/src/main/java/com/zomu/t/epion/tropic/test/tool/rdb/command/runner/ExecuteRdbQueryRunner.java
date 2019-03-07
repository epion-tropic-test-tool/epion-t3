package com.zomu.t.epion.tropic.test.tool.rdb.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.custom.configuration.resolver.CustomConfigurationTypeIdResolver;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.holder.CustomConfigurationHolder;
import com.zomu.t.epion.tropic.test.tool.rdb.command.model.ExecuteRdbQuery;
import com.zomu.t.epion.tropic.test.tool.rdb.configuration.model.RdbConnectionConfiguration;
import com.zomu.t.epion.tropic.test.tool.rdb.message.RdbMessages;
import com.zomu.t.epion.tropic.test.tool.rdb.util.RdbAccessUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * RDBに対してクエリー実行処理．
 *
 * @author takashno
 */
public class ExecuteRdbQueryRunner extends AbstractCommandRunner<ExecuteRdbQuery> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(ExecuteRdbQuery command, Logger logger) throws Exception {

        // 接続先設定を参照
        RdbConnectionConfiguration rdbConnectionConfiguration =
                referConfiguration(command.getRdbConnectConfigRef());

        // クエリー文字列を取得
        String query = command.getValue();

        // クエリーは必須
        if (StringUtils.isEmpty(query)) {
            throw new SystemException(RdbMessages.RDB_ERR_0001);
        }

        // 複数行の場合は、セミコロンで区切られている
        String[] queries = query.split(";");

        // 複数行か判断する
        boolean multiQuery = queries.length > 1;

        // データソースを取得
        DataSource dataSource = RdbAccessUtils.getInstance().getDataSource(rdbConnectionConfiguration);

        try (Connection conn = dataSource.getConnection()) {
            for (String q : queries) {
                if (StringUtils.isNotEmpty(q)) {
                    try (PreparedStatement statement = conn.prepareStatement(q)) {
                        statement.execute(q);
                    }
                }
            }
        } catch (SQLException e) {
            throw new SystemException(RdbMessages.RDB_ERR_0002, e);
        }

        return CommandResult.getSuccess();
    }

}
