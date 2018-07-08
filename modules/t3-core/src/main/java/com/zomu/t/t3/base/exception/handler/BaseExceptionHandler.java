package com.zomu.t.t3.base.exception.handler;

import com.zomu.t.t3.base.context.BaseContext;
import com.zomu.t.t3.base.message.BaseMessages;
import com.zomu.t.t3.core.exception.ScenarioParseException;
import com.zomu.t.t3.core.exception.handler.ExceptionHandler;
import com.zomu.t.t3.core.message.MessageManager;
import com.zomu.t.t3.core.type.ExitCode;
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
            System.exit(ExitCode.SCENARIO_ERROR.getExitCode());
        }

    }

}
