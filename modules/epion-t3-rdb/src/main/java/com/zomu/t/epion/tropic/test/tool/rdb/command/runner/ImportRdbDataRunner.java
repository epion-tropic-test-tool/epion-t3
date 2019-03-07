package com.zomu.t.epion.tropic.test.tool.rdb.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.rdb.command.model.ImportRdbData;
import com.zomu.t.epion.tropic.test.tool.rdb.configuration.model.RdbConnectionConfiguration;
import com.zomu.t.epion.tropic.test.tool.rdb.message.RdbMessages;
import com.zomu.t.epion.tropic.test.tool.rdb.type.DataSetType;
import com.zomu.t.epion.tropic.test.tool.rdb.type.OperationType;
import org.apache.commons.lang3.StringUtils;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.slf4j.Logger;

import javax.swing.text.html.parser.Entity;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ImportRdbDataRunner extends AbstractCommandRunner<ImportRdbData> {

    @Override
    public CommandResult execute(ImportRdbData command, Logger logger) throws Exception {

        // 接続先設定を参照
        RdbConnectionConfiguration rdbConnectionConfiguration =
                referConfiguration(command.getRdbConnectConfigRef());

        // DataSetの配置パスを取得
        String dataSet = command.getValue();

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
        DataSetType dataSetType = DataSetType.valueOfByValue(command.getDataSetType());

        // データセット種別が解決できなかった場合はエラー
        if (dataSetType == null) {
            throw new SystemException(RdbMessages.RDB_ERR_0007, command.getDataSetType());
        }

        // データセット読み込み
        IDataSet iDataSet = null;

        try (FileInputStream fis = new FileInputStream(dataSetPath.toFile())) {

            switch (dataSetType) {
                case CSV:
                    // TODO
                    // iDataSet = new CsvDataSet(dataSetPath.toFile());
                    //break;
                    throw new SystemException(RdbMessages.RDB_ERR_0008);
                case XML:
                    FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
                    builder.setColumnSensing(true);
                    iDataSet = builder.build(fis);
                    break;
                case EXCEL:
                    iDataSet = new XlsDataSet(fis);
                    break;
                default:
                    // ありえない
                    break;
            }

        }

        // バインド
        if (command.isBind()) {
            iDataSet = new ReplacementDataSet(iDataSet);

            // プロファイル定数
            for (Map.Entry<String, String> entry : getProfileConstants().entrySet()) {
                ((ReplacementDataSet) iDataSet).addReplacementObject(
                        String.format("${%s}", entry.getKey()), entry.getValue());
            }

            // グローバル変数
            for (Map.Entry<String, Object> entry : getGlobalScopeVariables().entrySet()) {
                ((ReplacementDataSet) iDataSet).addReplacementObject(
                        String.format("${%s.%s}", "global", entry.getKey()),
                        entry.getValue().toString());
            }

            // シナリオ変数
            for (Map.Entry<String, Object> entry : getScenarioScopeVariables().entrySet()) {
                ((ReplacementDataSet) iDataSet).addReplacementObject(
                        String.format("${%s.%s}", "scenario", entry.getKey()),
                        entry.getValue().toString());
            }

            // Flow変数
            for (Map.Entry<String, Object> entry : getFlowScopeVariables().entrySet()) {
                ((ReplacementDataSet) iDataSet).addReplacementObject(
                        String.format("${%s.%s}", "flow", entry.getKey()),
                        entry.getValue().toString());
            }

        }

        // オペレーションを特定
        OperationType operationType =
                OperationType.valueOfByValue(command.getOperation().toLowerCase());

        switch (operationType) {

            case INSERT:
                break;

            case CLEAN_INSERT:
                break;

            case UPDATE:
                break;

            case REFRESH:
                break;

            default:
                // データインポートとは関係のないオペレーションのためエラー
                break;

        }

        return CommandResult.getSuccess();

    }


}
