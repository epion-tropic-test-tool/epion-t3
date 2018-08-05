package com.zomu.t.epion.tropic.test.tool.core.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.UUID;

/**
 * エビデンス情報.
 *
 * @author takashno
 */
@Getter
@Setter
public class EvidenceInfo implements Serializable {

    /**
     * エビデンスID.
     * 内部的に割り振っているのみ
     */
    private UUID executeScenarioId = UUID.randomUUID();

    /**
     * 完全シナリオ名称.
     * Full Query Scenario Name.
     */
    private String fqsn;

    /**
     * 完全プロセス名称.
     * Full Query Process Name.
     */
    private String fqpn;

    /**
     * エビデンス名.
     * ユーザーがつけるエビデンスへの論理名.
     */
    private String name;

    /**
     * パス.
     */
    private Path path;

}
