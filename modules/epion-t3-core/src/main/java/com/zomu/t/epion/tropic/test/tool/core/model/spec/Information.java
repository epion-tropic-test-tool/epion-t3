package com.zomu.t.epion.tropic.test.tool.core.model.spec;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Information implements Serializable {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    @NonNull
    private String name;

    private String customPackage;

    private List<Content> summary;

    private List<Content> description;

}
