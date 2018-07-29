package com.zomu.t.epion.tropic.test.tool.base.exception.handler;

import com.zomu.t.epion.tropic.test.tool.base.message.BaseMessages;
import com.zomu.t.epion.tropic.test.tool.base.context.BaseContext;
import com.zomu.t.epion.tropic.test.tool.core.exception.ScenarioParseException;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.exception.handler.ExceptionHandler;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.core.type.ExitCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class BaseExceptionHandler implements ExceptionHandler<BaseContext> {

    /**
     * シングルトンインスタンス.
     */
    private static final BaseExceptionHandler instance = new BaseExceptionHandler();

    private BaseExceptionHandler() {
        // Do Nothing...
    }

    public static BaseExceptionHandler getInstance() {
        return instance;
    }

    @Override
    public void handle(final BaseContext context, final Throwable t) {

        MessageManager messageManager = MessageManager.getInstance();

        //t.printStackTrace();

        // シナリオ不正
        if (ScenarioParseException.class.isAssignableFrom(t.getClass())) {
            ScenarioParseException spe = ScenarioParseException.class.cast(t);
            spe.getErrors().forEach(x -> {
                String msg = null;
                if (x.getTarget() != null) {
                    if (x.getValue() != null) {
                        msg = messageManager.getMessage(BaseMessages.BASE_ERR_9004, x.getFilePath(), x.getMessage(), x.getTarget(), x.getValue());
                    } else {
                        msg = messageManager.getMessage(BaseMessages.BASE_ERR_9003, x.getFilePath(), x.getMessage(), x.getTarget());
                    }
                } else {
                    msg = messageManager.getMessage(BaseMessages.BASE_ERR_9002, x.getFilePath(), x.getMessage());
                }
                log.error(msg);
            });
        }

        // システムエラー
        if (SystemException.class.isAssignableFrom(t.getClass())) {
            SystemException se = SystemException.class.cast(t);
            log.error(se.getMessage());
        }


    }

}
