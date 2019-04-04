package com.zomu.t.epion.tropic.test.tool.ssh.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 認証方式.
 */
@Getter
@AllArgsConstructor
public enum AuthType {

    /**
     * パスワード認証方式.
     */
    USER_PASSWORD("password"),

    /**
     * 公開鍵認証方式.
     */
    PUBLIC_KEY("public_key");

    /**
     * 値.
     */
    private String value;

    /**
     * 値から列挙子を取得.
     *
     * @param value
     * @return
     */
    public static AuthType valueOfByValue(final String value) {
        return Arrays.stream(values()).filter(x -> x.value.equals(value)).findFirst().orElse(null);
    }

}
