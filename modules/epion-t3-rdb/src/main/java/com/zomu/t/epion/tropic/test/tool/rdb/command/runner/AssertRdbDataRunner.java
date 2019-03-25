package com.zomu.t.epion.tropic.test.tool.rdb.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.rdb.bean.AssertTargetTable;
import com.zomu.t.epion.tropic.test.tool.rdb.command.model.AssertRdbData;
import com.zomu.t.epion.tropic.test.tool.rdb.message.RdbMessages;
import com.zomu.t.epion.tropic.test.tool.rdb.type.DataSetType;
import com.zomu.t.epion.tropic.test.tool.rdb.util.DataSetUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.UnknownDataType;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * RDBに対してデータセットをインポート実行処理.
 *
 * @author takashno
 */
@Slf4j
public class AssertRdbDataRunner extends AbstractCommandRunner<AssertRdbData> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(AssertRdbData command, Logger logger) throws Exception {

        // 期待値DataSet
        IDataSet expected = getExpectedDataSet(command, logger);

        // 結果値DataSet
        IDataSet actual = getActualDataSet(command, logger);

        // アサート対象のテーブル情報をループ
        for (AssertTargetTable assertTargetTable : command.getTables()) {

            try {

                logger.debug("RdbDataAssert -> Table: {}", assertTargetTable.getTable());

                // 期待値Table
                ITable expectedTable = expected.getTable(assertTargetTable.getTable());
                // 期待値TableMetaData
                ITableMetaData expectedMetaData = expectedTable.getTableMetaData();

                // 結果値Table
                ITable actualTable = actual.getTable(assertTargetTable.getTable());
                // 結果値TableMetaData
                ITableMetaData actualMetaData = actualTable.getTableMetaData();

                // ----------------------------------------
                // カラム数アサート
                // ----------------------------------------
                if (expectedMetaData.getColumns().length != actualMetaData.getColumns().length) {
                    // カラム数不一致
                    // TODO：エラー（次に行く）
                }

                // ----------------------------------------
                // 型アサート
                // ----------------------------------------
                Map<String, DataType> dataTypeMap = new HashMap<>();
                for (Column expectedCol : expectedMetaData.getColumns()) {

                    Column actualCol = null;

                    // 期待値カラムのインデックスを取得
                    int expectedColIndex = -999;
                    expectedColIndex = expectedMetaData.getColumnIndex(expectedCol.getColumnName());

                    // 結果値カラムのインデックスを取得（期待値カラム名と一致する結果値カラムを取得）
                    int actualColIndex = -111;
                    actualColIndex = actualMetaData.getColumnIndex(expectedCol.getColumnName());

                    if (expectedColIndex != actualColIndex) {
                        // カラム順序不一致
                        // TODO：エラー（次に行く）
                    }

                    // カラム順序が一致したため、結果値カラムを取得する
                    actualCol = actualMetaData.getColumns()[actualColIndex];


                    if (UnknownDataType.class.isAssignableFrom(expectedCol.getDataType().getClass())) {
                        // 期待値カラムの型が不明なため、結果値カラムの型を使用
                        dataTypeMap.put(expectedCol.getColumnName(), actualCol.getDataType());
                    } else {

                        if (UnknownDataType.class.isAssignableFrom(actualCol.getDataType().getClass())) {
                            // 結果値カラムの型が不明なため、期待値カラムの型を使用
                            dataTypeMap.put(expectedCol.getColumnName(), expectedCol.getDataType());
                        } else {
                            // 型の確認を行う
                            if (!expectedCol.getDataType().getClass().isAssignableFrom(
                                    actualCol.getDataType().getClass())) {
                                // 型の不一致
                                // TODO：エラー（次に行く）
                            }
                        }
                    }
                }


                // エラーため込んでエラーがあれば終了
                // if () {
                //
                // }

                // ----------------------------------------
                // 件数アサート
                // ----------------------------------------

                // ----------------------------------------
                // データアサート
                // ----------------------------------------
                List<String> assertColumns = Arrays.stream(expectedMetaData.getColumns())
                        .filter(x -> !assertTargetTable.getIgnoreColumns().contains(x.getColumnName()))
                        .map(x -> x.getColumnName())
                        .collect(Collectors.toList());

                for (int rowCount = 0;
                     (rowCount < expectedTable.getRowCount() && rowCount < actualTable.getRowCount());
                     rowCount++) {

                    for (String column : assertColumns) {

                        // 期待値
                        Object expectedValue = expectedTable.getValue(rowCount, column);

                        // 結果値
                        Object actualValue = actualTable.getValue(rowCount, column);

                        if (dataTypeMap.containsKey(column)) {
                            DataType dataType = dataTypeMap.get(column);

                            if (dataType.compare(expectedValue, actualValue) == 0) {
                                // OK
                            } else {
                                // NG
                            }

                        } else {
                            // TODO：エラー処理
                            throw new SystemException("");
                        }
                    }
                }


            } finally {

            }

        }

        return CommandResult.getSuccess();

    }


    /**
     * 期待値DataSet読込.
     *
     * @param command コマンド
     * @param logger  ロガー
     * @return 期待値DataSet
     */
    private IDataSet getExpectedDataSet(AssertRdbData command, Logger logger) {

        // DataSetの配置パスを取得
        String dataSet = command.getExpectedDataSetPath();

        // DataSetの配置パスは必須
        if (StringUtils.isEmpty(dataSet)) {
            throw new SystemException(RdbMessages.RDB_ERR_0005);
        }

        // DataSetの配置パスを解決
        Path dataSetPath = Paths.get(getCommandBelongScenarioDirectory(), dataSet);

        // DataSetの配置パスが存在しなかった場合はエラー
        if (Files.notExists(dataSetPath)) {
            throw new SystemException(RdbMessages.RDB_ERR_0006, dataSetPath.toString());
        }

        // データセット種別
        DataSetType dataSetType = DataSetType.valueOfByValue(command.getExpectedDataSetType());

        // データセット種別が解決できなかった場合はエラー
        if (dataSetType == null) {
            throw new SystemException(RdbMessages.RDB_ERR_0007, command.getExpectedDataSetType());
        }

        // データセット読み込み
        IDataSet iDataSet = DataSetUtils.getInstance().readDataSet(dataSetPath, dataSetType);

        return iDataSet;

    }

    /**
     * 結果値DataSet読込.
     *
     * @param command コマンド
     * @param logger  ロガー
     * @return 結果値DataSet
     */
    private IDataSet getActualDataSet(AssertRdbData command, Logger logger) {

        // 結果値参照のFlowIDを取得
        String flowId = command.getActualFlowId();

        // DataSetの配置パスは必須
        if (StringUtils.isEmpty(flowId)) {
            throw new SystemException(RdbMessages.RDB_ERR_0018);
        }

        // 結果値DataSetの配置パスを解決
        Path dataSetPath = referFileEvidence(flowId);

        // DataSetの配置パスが存在しなかった場合はエラー
        if (Files.notExists(dataSetPath)) {
            throw new SystemException(RdbMessages.RDB_ERR_0006, dataSetPath.toString());
        }

        // データセット種別
        DataSetType dataSetType = DataSetType.valueOfByValue(command.getActualDataSetType());

        // データセット種別が解決できなかった場合はエラー
        if (dataSetType == null) {
            throw new SystemException(RdbMessages.RDB_ERR_0007, command.getActualDataSetType());
        }

        // データセット読み込み
        IDataSet iDataSet = DataSetUtils.getInstance().readDataSet(dataSetPath, dataSetType);

        return iDataSet;
    }


}
