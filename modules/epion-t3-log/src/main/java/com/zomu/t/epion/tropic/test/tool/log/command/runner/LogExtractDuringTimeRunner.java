package com.zomu.t.epion.tropic.test.tool.log.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.log.command.model.LogExtractDuringTime;
import com.zomu.t.epion.tropic.test.tool.log.message.LogMessages;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ログの抽出処理.
 * Flowの実行時間に合致するログを抽出する.
 *
 * @author takashno
 */
public class LogExtractDuringTimeRunner extends AbstractCommandRunner<LogExtractDuringTime> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(LogExtractDuringTime command, Logger logger) throws Exception {

        // 対象は必須
        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(LogMessages.LOG_ERR_9001);
        }

        // 抽出正規表現パターンは必須
        if (StringUtils.isEmpty(command.getExtractPattern())) {
            throw new SystemException(LogMessages.LOG_ERR_9003);
        }

        // パターンをコンパイル
        Pattern extractPattern = Pattern.compile(command.getExtractPattern());

        // 日付フォーマット
        SimpleDateFormat sdf = new SimpleDateFormat(command.getDatePattern());

        // ファイル読み込み
        Path logFilePath = referFileEvidence(command.getTarget());
        List<String> lines = Files.readAllLines(logFilePath, Charset.forName(command.getEncoding()));

        List<String> extractedLines = new ArrayList<>();

        // Flow開始日時
        LocalDateTime startDate = referFlowStartDate(command.getTargetFlow());

        // Flow終了日時
        LocalDateTime endDate = referFlowEndDate(command.getTargetFlow());

        boolean logFindAnyFlg = false;

        for (String line : lines) {

            Matcher m = extractPattern.matcher(line);

            if (m.find()) {

                logger.debug("Find extract log pattern.");

                // 日付を抽出
                String extractDateString = m.group(command.getGroup());

//                try {

                    //Date extractDate = sdf.parse(extractDateString);
                    LocalDateTime extractDate = LocalDateTime.parse(extractDateString, DateTimeFormatter.ofPattern(command.getDatePattern()));

                    if (startDate.compareTo(extractDate) == 0 || endDate.compareTo(extractDate) == 0) {
                        // 開始もしくは終了と等しい
                        logFindAnyFlg = true;
                        extractedLines.add(line);
                    } else if (startDate.compareTo(extractDate) < 0 && endDate.compareTo(extractDate) > 0) {
                        // 開始より後かつ終了より前
                        logFindAnyFlg = true;
                        extractedLines.add(line);
                    } else {
                        logFindAnyFlg = false;
                    }

//                } catch (ParseException e) {
//                    throw new SystemException(LogMessages.LOG_ERR_9004, extractDateString, command.getDatePattern());
//                }

            } else {

                logger.debug("no match extract log pattern.");

                if (logFindAnyFlg) {
                    // 一度ログ解析を始めた後、
                    // 日付パターンと合致しない場合は、スタックトレース等の複数行のログとみなし抽出対象に含める
                    extractedLines.add(line);
                }
            }

        }

        // 上書き保存
        Files.write(logFilePath, extractedLines, Charset.forName(command.getEncoding()));

        return CommandResult.getSuccess();
    }

}
