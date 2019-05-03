package com.epion_t3.ssh.util;

import com.epion_t3.core.exception.SystemException;
import com.epion_t3.ssh.configuration.model.SshConnectionConfiguration;
import com.epion_t3.ssh.context.SshExecuteContext;
import com.epion_t3.ssh.messages.SshMessages;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.future.AuthFuture;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.config.keys.loader.pem.PEMResourceParserUtils;
import org.apache.sshd.common.config.keys.loader.pem.RSAPEMResourceKeyPairParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public final class SshUtils {

    /**
     * シングルトンインスタンス.
     */
    private static final SshUtils instance = new SshUtils();

    /**
     * プライベートコンストラクタ.
     */
    private SshUtils() {
        // Do Nothing...
    }

    /**
     * シングルトンインスタンスを取得.
     *
     * @return シングルトンインスタンス
     */
    public static SshUtils getInstance() {
        return instance;
    }


    public SshExecuteContext connect(SshConnectionConfiguration sshConnectionConfiguration) {
        SshExecuteContext context = new SshExecuteContext();

        // クライアント起動
        SshClient sshClient = SshClient.setUpDefaultClient();
        context.setSshClient(sshClient);
        context.getSshClient().start();

        // 接続
        try {
            ClientSession session = sshClient.connect(
                    sshConnectionConfiguration.getUser(),
                    sshConnectionConfiguration.getHost(),
                    sshConnectionConfiguration.getPort())
                    .verify(7L, TimeUnit.SECONDS).getSession();
            context.setClientSession(session);
        } catch (IOException e) {
            throw new SystemException(SshMessages.SSH_ERR_0003, sshConnectionConfiguration.getId());
        }

        // 認証
//        context.getClientSession().add
//        context.getClientSession().addPasswordIdentity(connInfo.getPassword());
//        AuthFuture authFuture = context.getClientSession().auth();
//        authFuture.verify(30L, TimeUnit.SECONDS);

        return context;
    }


    public Collection<KeyPair> readPublicKey(Path publicKey) {

        Collection<KeyPair> keyPairs = null;

        if (publicKey != null && Files.exists(publicKey)) {
//            keyPairs = PEMResourceParserUtils.getPEMResourceParserByAlgorithm();

        } else {
//            throw new SystemException();
        }

        return keyPairs;
    }

}
