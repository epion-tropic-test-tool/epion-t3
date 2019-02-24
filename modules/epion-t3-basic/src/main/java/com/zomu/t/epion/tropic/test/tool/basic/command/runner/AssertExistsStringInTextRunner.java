package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.AssertExistsStringInText;
import com.zomu.t.epion.tropic.test.tool.basic.command.model.StringConcat;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssertExistsStringInTextRunner extends AbstractCommandRunner<AssertExistsStringInText> {

    @Override
    public void execute(
            AssertExistsStringInText command,
            Logger logger) throws Exception {


        Path targetFile = referFileEvidence(command.getTarget());

        Pattern p = Pattern.compile(command.getValue());
        boolean existsFlg = false;
        List<String> lineList = null;
        try {
            lineList = FileUtils.readLines(targetFile.toFile(), command.getEncoding());
            for (String line : lineList) {
                Matcher m = p.matcher(line);
                if (m.find()) {
                    existsFlg = true;
                    break;
                }
            }

            if (!existsFlg) {
                RuntimeException e = new RuntimeException("指定パターンに合致する文字列が含まれていません。。エビデンスID:["
                        + existsStrInLogAssert.getTargetId() + "] ファイル:[" + et.getFile().getPath()
                        + "] 指定パターン:[" + command.getValue() + "]");
                context.setTh(e);
                throw e;
            }

        } catch (IOException e) {
            context.setTh(e);
            throw new RuntimeException(e);
        }

    } else

    {

        boolean existsFlg = false;

        List<String> lineList = null;
        try {
            lineList = FileUtils.readLines(et.getFile(), getCharset(et));
            for (String line : lineList) {
                if (StringUtils.contains(line, existsStrInLogAssert.getPattern())) {
                    existsFlg = true;
                    break;
                }
            }

            if (!existsFlg) {
                RuntimeException e = new RuntimeException("指定パターンに合致する文字列が含まれていません。。エビデンスID:["
                        + existsStrInLogAssert.getTargetId() + "] ファイル:[" + et.getFile().getPath()
                        + "] 指定パターン:[" + existsStrInLogAssert.getPattern() + "]");
                context.setTh(e);
                throw e;
            }

        } catch (IOException e) {
            context.setTh(e);
            throw new RuntimeException(e);
        }

    }

			LOGGER.info("アサート結果：OK");

} else{
        if(context.getMode()==ModeType.ASSERT_EXECUTE){
        LOGGER.info("※");
        LOGGER.info("※ 本アサートはエビデンスとの一致を取るアサートのためアサートモードでは実施できません！");
        LOGGER.info("※");
        }else{
        RuntimeException e=new RuntimeException(
        "対象のエビデンスファイルが存在しません。エビデンスID:["+existsStrInLogAssert.getTargetId()+"]");
        context.setTh(e);
        throw e;
        }
        }

        }
        }
