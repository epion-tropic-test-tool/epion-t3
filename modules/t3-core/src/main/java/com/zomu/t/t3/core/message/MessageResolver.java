package com.zomu.t.t3.core.message;

import com.google.common.reflect.ClassPath;
import com.zomu.t.t3.core.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * メッセージ解決クラス.
 */
@Slf4j
public final class MessageResolver {

    /**
     * メッセージ解決処理.
     */
    private static final MessageResolver instance = new MessageResolver();

    /**
     * リソースバンドルの保持リスト.
     */
    private final List<ResourceBundle> resourceBundles = new ArrayList<>();

    /**
     * メッセージのリソースファイルを
     */
    public static final String MESSAGE_SUFFIX_PATTERN = "_messages.properties";

    /**
     * プライベートコンストラクタ.
     */
    private MessageResolver() {

        // 読み込み
        load();
    }

    /**
     * インスタンスを取得する.
     *
     * @return シングルトンインスタンス
     */
    public static MessageResolver getInstance() {
        return instance;
    }

    /**
     * メッセージのロード.
     */
    private void load() {

        log.debug("start message resolver initial load.");

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<ClassPath.ResourceInfo> messageResources
                = null;
        try {
            messageResources = ClassPath.from(loader)
                    .getResources().stream()
                    .filter(info -> info.getResourceName().endsWith(MESSAGE_SUFFIX_PATTERN))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new SystemException(e);
        }

        for (ClassPath.ResourceInfo resource : messageResources) {
            try {
                ResourceBundle rb =
                        ResourceBundle.getBundle(FilenameUtils.getBaseName(resource.getResourceName()), Locale.getDefault());
                resourceBundles.add(rb);
                log.debug("load resource bundle: {}", resource.getResourceName());
            } catch (MissingResourceException e) {
                log.debug("can not load resource bundle: {}", resource.getResourceName());
                continue;
            }
        }
        log.debug("end message resolver initial load.");
    }

}
