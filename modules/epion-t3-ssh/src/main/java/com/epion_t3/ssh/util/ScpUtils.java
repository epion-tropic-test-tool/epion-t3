package com.epion_t3.ssh.util;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import com.epion_t3.ssh.configuration.model.SshConnectionConfiguration;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * SCPによる操作を行うユーティリティクラス.
 *
 * @author takashno
 */
public final class ScpUtils {

    /**
     * シングルトンインスタンス.
     */
    private static final ScpUtils instance = new ScpUtils();

    /**
     * プライベートコンストラクタ.
     */
    private ScpUtils() {
        // Do Nothing...
    }

    /**
     * インスタンスを取得.
     *
     * @return シングルトンインスタンス
     */
    public static ScpUtils getInstance() {
        return instance;
    }

    /**
     * SCPによるGETを行う.
     * このメソッドは、ユーザ名/パスワードによる認証に対応.
     *
     * @param sshConnectionConfiguration
     * @param remotePath
     * @param localPath
     * @throws IOException
     */
    public void get(
            SshConnectionConfiguration sshConnectionConfiguration,
            String remotePath,
            String localPath)
            throws IOException {
        Connection connection = null;
        try {
            connection = new Connection(sshConnectionConfiguration.getHost(),
                    Integer.valueOf(sshConnectionConfiguration.getPort()));
            connection.connect();
            if (StringUtils.isNotEmpty(sshConnectionConfiguration.getPemFilePath())) {
                connection.authenticateWithPublicKey(
                        sshConnectionConfiguration.getUser(),
                        new File(sshConnectionConfiguration.getPemFilePath()), null);
            } else {
                connection.authenticateWithPassword(
                        sshConnectionConfiguration.getUser(),
                        sshConnectionConfiguration.getPassword());
            }
            SCPClient scp = connection.createSCPClient();

            OutputStream os = new FileOutputStream(new File(localPath));
            scp.get(remotePath, os);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * PUT
     *
     * @param sshConnectionConfiguration
     * @param remoteDirPath
     * @param localPath
     * @throws IOException
     */
    public void put(
            SshConnectionConfiguration sshConnectionConfiguration,
            String remoteDirPath,
            String localPath)
            throws IOException {
        Connection connection = null;
        try {
            connection = new Connection(
                    sshConnectionConfiguration.getHost(),
                    Integer.valueOf(sshConnectionConfiguration.getPort()));
            connection.connect();
            if (StringUtils.isNotEmpty(sshConnectionConfiguration.getPemFilePath())) {
                connection.authenticateWithPublicKey(
                        sshConnectionConfiguration.getUser(),
                        new File(sshConnectionConfiguration.getPemFilePath()), null);
            } else {
                connection.authenticateWithPassword(
                        sshConnectionConfiguration.getUser(),
                        sshConnectionConfiguration.getPassword());
            }
            SCPClient scp = connection.createSCPClient();
            scp.put(localPath, remoteDirPath);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}
