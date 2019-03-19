package com.zomu.t.epion.tropic.test.tool.rdb.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.rdb.command.model.ExportRdbData;
import com.zomu.t.epion.tropic.test.tool.rdb.bean.TargetTable;
import com.zomu.t.epion.tropic.test.tool.rdb.configuration.model.RdbConnectionConfiguration;
import com.zomu.t.epion.tropic.test.tool.rdb.message.RdbMessages;
import com.zomu.t.epion.tropic.test.tool.rdb.type.DataSetType;
import com.zomu.t.epion.tropic.test.tool.rdb.util.RdbAccessUtils;
import com.zomu.t.epion.tropic.test.tool.rdb.writer.XlsxDataSetWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.slf4j.Logger;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.sql.SQLException;

/**
 * RDBに対してデータセットをインポート実行処理.
 *
 * @author takashno
 */
@Slf4j
public class ExportRdbDataRunner extends AbstractCommandRunner<ExportRdbData> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(ExportRdbData command, Logger logger) throws Exception {

        // 接続先設定を参照
        RdbConnectionConfiguration rdbConnectionConfiguration =
                referConfiguration(command.getRdbConnectConfigRef());

        // データセット種別
        DataSetType dataSetType = DataSetType.valueOfByValue(command.getDataSetType());

        // データセット種別が解決できなかった場合はエラー
        if (dataSetType == null) {
            throw new SystemException(RdbMessages.RDB_ERR_0007, command.getDataSetType());
        }

        // データセット読み込み
        IDataSet iDataSet = null;

        IDatabaseConnection conn = null;
        try {
            // コネクションを取得
            conn = RdbAccessUtils.getInstance().getDatabaseConnection(rdbConnectionConfiguration);

            // クエリーデータセットを作成
            iDataSet = new QueryDataSet(conn);

            // 対象テーブルを登録
            for (TargetTable t : command.getTables()) {
                ((QueryDataSet) iDataSet).addTable(t.getTable(), t.getQuery());
            }

            // データセットの種類によって出力処理を行う
            switch (dataSetType) {
                case CSV:
                    // TODO
                    // iDataSet = new CsvDataSet(dataSetPath.toFile());
                    //break;
                    throw new SystemException(RdbMessages.RDB_ERR_0008);
                case XML:
                    Path flatXmlPath = getEvidencePath("export.xml");
                    try (OutputStream os = new FileOutputStream(flatXmlPath.toFile())) {
                        FlatXmlWriter writer = new FlatXmlWriter(os);
                        writer.write(iDataSet);
                    }
                    registrationFileEvidence(flatXmlPath);
                    break;
                case EXCEL:
                    Path xlsxPath = getEvidencePath("export.xlsx");
                    try (OutputStream os = new FileOutputStream(xlsxPath.toFile())) {
                        XlsxDataSetWriter writer = new XlsxDataSetWriter();
                        writer.write(iDataSet, os);
                    }
                    registrationFileEvidence(xlsxPath);
                    break;
                default:
                    // ありえない
                    break;
            }
        } catch (DatabaseUnitException e) {
            log.debug("Error Occurred...", e);
            throw new SystemException(e, RdbMessages.RDB_ERR_0011);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Ignore
                    log.trace("Error Occurred... -> Ignore", e);
                }
            }
        }

        return CommandResult.getSuccess();

    }


}
