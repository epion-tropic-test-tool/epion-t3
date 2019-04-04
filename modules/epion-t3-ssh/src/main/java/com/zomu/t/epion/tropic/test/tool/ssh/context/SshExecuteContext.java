package com.zomu.t.epion.tropic.test.tool.ssh.context;

import lombok.Getter;
import lombok.Setter;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.session.ClientSession;

import java.io.Serializable;

@Getter
@Setter
public class SshExecuteContext implements Serializable {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * SSHクライアント.
     */
    private SshClient sshClient;

    /**
     * SSHクライアントセッション.
     */
    private ClientSession clientSession;

    /**
     * SSHクライアントチャンネル.
     */
    private ClientChannel clientChannel;


}
