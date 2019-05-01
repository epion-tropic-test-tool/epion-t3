package com.zomu.t.epion.tropic.test.tool.ftp.configuration;

import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Configuration;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 * @author takashno
 */
@Getter
@Setter
public class FtpConnectionConfiguration extends Configuration {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * ホスト.
     */
    @NotEmpty
    private String host = null;

    /**
     * ポート.
     */
    @NotEmpty
    private Integer port = null;

    /**
     * ユーザ.
     */
    @NotEmpty
    private String username = null;

    /**
     * パスワード.
     */
    @NotEmpty
    private String password = null;

}
