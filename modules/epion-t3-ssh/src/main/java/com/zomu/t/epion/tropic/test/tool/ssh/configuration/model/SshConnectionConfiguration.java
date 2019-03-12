package com.zomu.t.epion.tropic.test.tool.ssh.configuration.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CustomConfigurationDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Configuration;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * SSH接続情報.
 *
 * @author takashno
 */
@Getter
@Setter
@CustomConfigurationDefinition(id = "SshConnectionConfiguration")
public class SshConnectionConfiguration extends Configuration {

    @NotEmpty
    private String host;

    @NotNull
    private Integer port;

    private String user;

    private String password;

    private String pemFilePath;

    @NotEmpty
    private String authType;
}
