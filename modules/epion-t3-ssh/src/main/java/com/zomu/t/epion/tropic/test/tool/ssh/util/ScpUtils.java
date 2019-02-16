package com.zomu.t.epion.tropic.test.tool.ssh.util;


public final class ScpUtils {


    /**
     * GET
     *
     * @param sshConnectInfo
     * @param remotePath
     * @param localPath
     * @throws IOException
     */
    public static void get(SshConnectInfo sshConnectInfo, String pemFilePath, String remotePath, String localPath)
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
     * @param remotePath
     * @param localPath
     * @throws IOException
     */
    public static void put(SshConnectInfo sshConnectInfo, String pemFilePath, String remoteDirPath, String localPath)
            throws IOException {
        Connection connection = null;
        try {
            connection = new Connection(sshConnectInfo.getHost(), Integer.valueOf(sshConnectInfo.getPort()));
            connection.connect();
            connection.authenticateWithPublicKey(sshConnectInfo.getUser(), new File(pemFilePath), null);
            SCPClient scp = connection.createSCPClient();
            scp.put(localPath, remoteDirPath);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


}
