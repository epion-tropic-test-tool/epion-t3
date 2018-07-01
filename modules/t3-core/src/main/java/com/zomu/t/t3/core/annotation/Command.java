package com.zomu.t.t3.core.annotation;

import com.zomu.t.t3.core.execution.resolver.CommandResolver;
import com.zomu.t.t3.core.execution.runner.CommandRunner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * T3のコマンドを表すアノテーション.
 *
 * @author takashno
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    /**
     * コマンド名.
     */
    String id();

    /**
     * コマンド実行処理クラス.
     */
    Class<? extends CommandRunner> runner();

}
