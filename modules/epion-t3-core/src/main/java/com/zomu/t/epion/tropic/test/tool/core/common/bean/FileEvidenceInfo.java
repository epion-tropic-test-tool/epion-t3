package com.zomu.t.epion.tropic.test.tool.core.common.bean;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

@Getter
@Setter
public class FileEvidenceInfo extends EvidenceInfo {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * パス.
     */
    private Path path;

    /**
     * 【レポート用】
     * シナリオディレクトリからの相対パス.
     */
    private String relativePath;

}
