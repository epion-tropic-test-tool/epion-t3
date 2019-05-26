package com.epion_t3.ssh.messages;

import com.epion_t3.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SshMessages implements Messages {

    SSH_ERR_0001("com.epion_t3.ssh.err.0001"),
    SSH_ERR_0002("com.epion_t3.ssh.err.0002"),
    SSH_ERR_0003("com.epion_t3.ssh.err.0003"),
    SSH_ERR_0004("com.epion_t3.ssh.err.0004"),;

    private String messageCode;

}
