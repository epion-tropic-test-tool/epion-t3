package com.epion_t3.ftp.messages;

import com.epion_t3.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * FTP関連のメッセージ.
 *
 * @author takashno
 */
@Getter
@AllArgsConstructor
public enum FtpMessages implements Messages {

    FTP_ERR_9001("com.zomu.t.epion.t3.ftp.err.9001"),
    FTP_ERR_9002("com.zomu.t.epion.t3.ftp.err.9002"),
    FTP_ERR_9003("com.zomu.t.epion.t3.ftp.err.9003"),;

    /**
     * メッセージコード.
     */
    private String messageCode;

}
