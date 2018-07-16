package com.zomu.t.t3.core.logging.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.zomu.t.t3.core.holder.ProcessLog;
import com.zomu.t.t3.core.holder.ProcessLoggingHolder;

import java.time.LocalDateTime;

/**
 * プロセスのログを収集するためのアペンダー.
 */
public class ProcessLoggingAppender extends AppenderBase<ILoggingEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void append(ILoggingEvent eventObject) {
        Level level = eventObject.getLevel();
        String message = eventObject.getMessage();
        LocalDateTime now = LocalDateTime.now();
        ProcessLoggingHolder.append(ProcessLog.builder()
                .level(level)
                .message(message)
                .dateTime(now).build());
    }

}
