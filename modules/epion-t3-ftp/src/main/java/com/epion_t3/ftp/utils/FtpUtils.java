package com.epion_t3.ftp.utils;

import com.epion_t3.core.exception.SystemException;
import com.epion_t3.ftp.configuration.FtpConnectionConfiguration;
import com.epion_t3.ftp.messages.FtpMessages;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

/**
 * @author takashno
 */
@Slf4j
public final class FtpUtils {

    /**
     * シングルトンインスタンス.
     */
    private static final FtpUtils instance = new FtpUtils();

    /**
     * プライベートコンストラクタ.
     */
    private FtpUtils() {
        // Do Nothing...
    }

    /**
     * @return
     */
    public static FtpUtils getInstance() {
        return instance;
    }

    /**
     * FTPクライアントを取得.
     *
     * @param configuration FTP接続先設定
     * @return {@link FTPClient}
     */
    public FTPClient getFTPClient(final FtpConnectionConfiguration configuration) {

        FTPClient client = null;
        try {
            client = new FTPClient();
            client.connect(configuration.getHost(), configuration.getPort());

            int retCode = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(retCode)) {
                throw new SystemException(FtpMessages.FTP_ERR_9001,
                        configuration.getHost(),
                        configuration.getPort());
            }

            if (!client.login(configuration.getUsername(), configuration.getPassword())) {
                throw new SystemException(FtpMessages.FTP_ERR_9002,
                        configuration.getHost(),
                        configuration.getPort(),
                        configuration.getUsername(),
                        configuration.getPassword());
            }

            // PASVモードに設定
            client.pasv();

            return client;

        } catch (IOException e) {
            throw new SystemException(FtpMessages.FTP_ERR_9002,
                    configuration.getHost(),
                    configuration.getPort(),
                    configuration.getUsername(),
                    configuration.getPassword(), e);
        }
    }

}
