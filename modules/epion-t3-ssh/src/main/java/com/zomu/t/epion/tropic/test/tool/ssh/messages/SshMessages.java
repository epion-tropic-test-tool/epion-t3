package com.zomu.t.epion.tropic.test.tool.ssh.messages;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SshMessages implements Messages {

    SSH_ERR_0001("com.zomu.t.epion.t3.ssh.err.0001"),
    SSH_ERR_0002("com.zomu.t.epion.t3.ssh.err.0002"),;


    private String messageCode;

}
