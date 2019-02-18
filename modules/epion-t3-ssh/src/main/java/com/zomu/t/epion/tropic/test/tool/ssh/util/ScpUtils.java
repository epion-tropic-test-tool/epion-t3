package com.zomu.t.epion.tropic.test.tool.ssh.util;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import com.zomu.t.epion.tropic.test.tool.ssh.bean.SshConnectInfo;

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
     * SCPによるGETを行う.
     * このメソッドは、ユーザ名/パスワードによる認証に対応.
     *
     * @param sshConnectInfo
     * @param remotePath
     * @param localPath
     * @throws IOException
     */
    public static void get(
            SshConnectInfo sshConnectInfo,
            String remotePath,
            String localPath)
            throws IOException {
        Connection connection = null;
        try {
            connection = new Connection(sshConnectInfo.getHost(), Integer.valueOf(sshConnectInfo.getPort()));
            connection.connect();
            connection.authenticateWithPassword(sshConnectInfo.getUser(), sshConnectInfo.getPassword());
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
     * SCPによるGETを行う.
     * このメソッドは、ユーザ名/鍵ファイル（pem）による認証に対応.
     *
     * @param sshConnectInfo
     * @param remotePath
     * @param localPath
     * @throws IOException
     */
    public static void get(
            SshConnectInfo sshConnectInfo,
            String pemFilePath,
            String remotePath,
            String localPath)
            throws IOException {
        Connection connection = null;
        try {
            connection = new Connection(sshConnectInfo.getHost(), Integer.valueOf(sshConnectInfo.getPort()));
            connection.connect();
            connection.authenticateWithPublicKey(sshConnectInfo.getUser(), new File(pemFilePath), null);
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
     * @param sshConnectInfo
     * @param remoteDirPath
     * @param localPath
     * @throws IOException
     */
    public static void put(
            SshConnectInfo sshConnectInfo,
            String remoteDirPath,
            String localPath)
            throws IOException {
        Connection connection = null;
        try {
            connection = new Connection(
                    sshConnectInfo.getHost(),
                    Integer.valueOf(sshConnectInfo.getPort()));
            connection.connect();
            connection.authenticateWithPassword(sshConnectInfo.getUser(), null);
            SCPClient scp = connection.createSCPClient();
            scp.put(localPath, remoteDirPath);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * PUT
     *
     * @param sshConnectInfo
     * @param remoteDirPath
     * @param localPath
     * @throws IOException
     */
    public static void put(
            SshConnectInfo sshConnectInfo,
            String pemFilePath,
            String remoteDirPath,
            String localPath)
            throws IOException {

        Connection connection = null;
        try {
            connection = new Connection(
                    sshConnectInfo.getHost(),
                    Integer.valueOf(sshConnectInfo.getPort()));
            connection.connect();
            connection.authenticateWithPublicKey(
                    sshConnectInfo.getUser(),
                    new File(pemFilePath), null);
            SCPClient scp = connection.createSCPClient();
            scp.put(localPath, remoteDirPath);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


}
