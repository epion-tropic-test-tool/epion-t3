package com.zomu.t.t3.model.scenario;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Information implements Serializable {

    @NonNull
    private String id;

    private String version;

    private String summary;

    private String description;

}
